package com.gilson.productms.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilson.productms.entity.ProductEntity;

@Repository
public interface ProductReprository extends JpaRepository<ProductEntity, String>{

}