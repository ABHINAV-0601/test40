package com.michaels.designhub.repository;

import com.michaels.designhub.entity.Framer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author Baojian Hong
 * @Date 2022/9/13 10:20
 * @Version 1.0
 */
@Repository
public interface FramersRepository extends JpaRepository<Framer, Integer> {

    @Query(nativeQuery = true, value = "SELECT sgi.id as store_glass_inventory_id, msg.dh_sku as sku, sgi.inventory_count,  msg.short_side, msg.long_side FROM material_sizegroup msg JOIN store_glass_inventory sgi ON sgi.material_sizegroup_id = msg.id WHERE sgi.store_id= :storeId")
    List<Map<String, Object>> getGlassInventory(@Param("storeId") String storeId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE store_glass_inventory SET inventory_count = :inventory_count WHERE id= :store_glass_inventory_id")
    int update(@Param("store_glass_inventory_id") Integer store_glass_inventory_id, @Param("inventory_count") Integer inventory_count);

    @Query(nativeQuery = true,value = "select id,username,firstname,lastname,created_at,update_at from framer where username = :username LIMIT 1")
    Framer getFramersByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update framer set update_at = :updated_at\\:\\:timestamp where username = :username")
    int updateFramers(@Param("updated_at") String updated_at, @Param("username") String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "Insert into framer(username,firstname,lastname,created_at,update_at) Values(:username,:firstname,:lastname,:updated_at\\:\\:timestamp,:updated_at\\:\\:timestamp)")
    int addFramers(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("updated_at") String updated_at, @Param("username") String username);
}
