package com.michaels.designhub.repository;

import com.michaels.designhub.entity.CatalogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogEntry, Long> {
}
