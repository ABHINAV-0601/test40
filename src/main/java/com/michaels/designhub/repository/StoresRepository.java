package com.michaels.designhub.repository;

import com.michaels.designhub.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Store, Long> {
    Store findStoreByStoreNumber(String storeNumber);
}
