package com.louis.xy.tax.repository;

import com.louis.xy.tax.model.entity.CalculatedProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalculatedProductRepository extends MongoRepository<CalculatedProduct, String> {
}
