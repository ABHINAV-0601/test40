package com.michaels.designhub.repository;

import com.michaels.designhub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDaoRepo extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true,
            value = "select get_orders_by_tracking_numbers ( " +
            ":storeNumber , cast(:trackingNumbers as text[]) )")
    Object getOrdersByTrackingNumbersFunction(@Param("storeNumber") String storeNumber, @Param("trackingNumbers") String trackingNumbers);

    @Query(nativeQuery = true,
            value = "select update_order_lineitem_shipping ( :updateDetails )")
    Object updateOrderLineItemShippingFunction(@Param("updateDetails") String updateDetails);
}
