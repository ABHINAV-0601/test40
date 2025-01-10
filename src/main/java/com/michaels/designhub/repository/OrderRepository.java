package com.michaels.designhub.repository;


import com.fasterxml.jackson.databind.JsonNode;
import com.michaels.designhub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@TypeDef(name = "jsonb",typeClass = JsonBin.class)
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "select get_gso_glasstype_req(:st\\:\\:json)")
    JsonNode get_gso_glasstype_req(@Param("st") String st);

    @Query(nativeQuery = true, value = "select get_gso_non_printed_layout(:st\\:\\:json)")
    JsonNode get_gso_non_printed_layout(@Param("st") String st);


    @Transactional
    @Modifying
    @Query(value = "update order_lineitem set custom_attributes = custom_attributes || jsonb_build_object(\'gso_date\',:st\\:\\:timestamp), update_at = :st\\:\\:timestamp where id in (:seleetIn)",nativeQuery = true)
    int updateOrderLineitem(@Param("st") String st, @Param("seleetIn") List<Integer> seleetIn);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "update order_lineitem_component_tracking set component_status = 6, \n" +
            "updated_at = current_date, \n" +
            "updated_user = :receivedBy, \n" +
            "updated_prc = 'SYSTEM' \n" +
            "where tracking_number in ( :trackingNumbers ) and component_status != 6" )
    int updateComponentStatusForTrackingNumbers(List<String> trackingNumbers, String receivedBy);



    @Query(nativeQuery = true,
            value = "select ol.order_lineitem_number\n" +
                    "from order_lineitem ol \n" +
                    "left join order_lineitem_component olc on ol.id  = olc.order_lineitem_id \n" +
                    "left join order_lineitem_component_tracking olct on olc.id  = olct.component_id \n" +
                    "where olct.tracking_number in (:trackingNumbers)\n" +
                    "and ol.order_lineitem_status_code in ('waiting')\n" +
                    "group by ol.order_lineitem_number " )
    List<String> fetchOrderLineItemNumbersFromTrackingNumberList(List<String> trackingNumbers);

    @Query(nativeQuery = true,
            value = "select \n" +
                    "aa.order_lineitem_number \n" +
                    "from (\n" +
                    "select ol.order_lineitem_number,\n" +
                    "count(olc.id) as compCount,\n" +
                    "count(case when \n" +
                    "olct.component_status = 6 or olct.component_status = 2 \n" +
                    "then olct.component_status else null end) as compStatusCount\n" +
                    "from order_lineitem ol \n" +
                    "left join order_lineitem_component olc on ol.id  = olc.order_lineitem_id \n" +
                    "left join order_lineitem_component_tracking olct on olc.id  = olct.component_id \n" +
                    "where\n" +
                    "ol.order_lineitem_number in (:orderLineNumbersList)\n" +
                    "and\n" +
                    "ol.order_lineitem_status_code in ('waiting')\n" +
                    "group by ol.order_lineitem_number \n" +
                    ") aa where aa.compCount = aa.compStatusCount" )
    List<String> fetchOrderLineItemNumbersWhenAllComponentsConfirmed(List<String> orderLineNumbersList);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value ="update order_lineitem set \n" +
                    "order_lineitem_status_code = 'received',\n" +
                    "update_at = now(), \n" +
                    "update_user = :receivedBy, \n" +
                    "update_prc = 'SYSTEM' \n" +
                    "where order_lineitem_number in (:orderLineItemNumbers) \n" +
                    "and order_lineitem_status_code = 'waiting'")
    int updateOrderLineItemStatusByOrderLineItemNumbers(List<String> orderLineItemNumbers,String receivedBy);
}
