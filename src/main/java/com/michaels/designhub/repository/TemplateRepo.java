package com.michaels.designhub.repository;

import com.michaels.designhub.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TemplateRepo extends JpaRepository<Template, Integer> {

    @Query(nativeQuery = true, value = "SELECT * from design_template where is_active = true and (type in ('global','default') or (type = 'local' and store_id = :storeId )) order by created_at desc ")
    List<Template> findTemplateByStoreId(String storeId);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE design_template SET is_active=false, update_at = :updateAt WHERE id= :templateId")
    int updateTemplate(@Param("templateId") Integer templateId, @Param("updateAt") Date updateAt);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE design_template SET is_active=false, update_at = :updateAt WHERE type = 'default' and is_active=true and store_id = :storeId ")
    int updateTemplateByTypeAndIsActive(Date updateAt,@Param("storeId") String storeId);


    @Query(nativeQuery = true, value = "select count(*) from design_template where type = :type and is_active = true")
    Integer selectTemplateByTypeAndIsActive(@Param("type") String type);


    @Query(nativeQuery = true, value = "select count(*) from design_template where type= :type and is_active = true and store_id = :storeId")
    int selectCoutByTypeAndIsActiveAndStoreId(@Param("type") String type, @Param("storeId") String storeId);
}