package com.louis.xy.tax.repository;

import com.louis.xy.tax.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findById(String id);
}
