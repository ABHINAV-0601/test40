package com.michaels.designhub.repository;

import com.michaels.designhub.entity.OfflinePIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@TypeDef(name = "jsonb",typeClass = JsonBin.class)
@Repository
public interface OfflinePINRepository extends JpaRepository<OfflinePIN, Long> {


    @Query(nativeQuery = true, value = "select id,encrypted_pin,hashed_pin, expiry_date,update_at from store_offline_login where store_id = :storeId LIMIT 1")
    List<OfflinePIN> selectByStoreId(String storeId);
}
