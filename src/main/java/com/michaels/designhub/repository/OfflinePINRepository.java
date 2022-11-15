package com.michaels.designhub.repository;

import com.michaels.designhub.entity.OfflinePIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface OfflinePINRepository extends JpaRepository<OfflinePIN, Long> {


    @Query(nativeQuery = true, value = "select id,encrypted_pin,hashed_pin, expiry_date,update_at from store_offline_login where store_id = :storeId LIMIT 1")
    List<OfflinePIN> selectByStoreId(String storeId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true , value = "update store_offline_login  set encrypted_pin = :encrypted_pin, hashed_pin = :hashed_pin,expiry_date = :expiry_date\\:\\:timestamp, update_at = :update_at\\:\\:timestamp  where store_id = :store_id")
    Integer upPin(@Param("store_id") String store_id, @Param("hashed_pin") String hashed_pin, @Param("encrypted_pin") String encrypted_pin, @Param("expiry_date") String expiry_date, @Param("update_at") String update_at);
}
