package com.trackservice.repository.catalog;

import com.trackservice.entity.product.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatalogRepository  extends JpaRepository<Catalog, Long> {
    List<Catalog> findAllByStoreId(@Param("store_id") Long storeId);
}
