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
}
