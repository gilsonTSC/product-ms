package com.gilson.productms.reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gilson.productms.entity.ProductEntity;

@Repository
public interface ProductReprository extends JpaRepository<ProductEntity, String>{

	@Query("select p from ProductEntity p " +
           " where ( upper(p.name) like concat('%', upper(:q), '%') or upper(p.description) like concat('%', upper(:q), '%') )" +
           "   and p.price >= :minPrice and p.price <= :maxPrice")
    List<ProductEntity> findByNameAndPrice(@Param("q") String q, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
	
}