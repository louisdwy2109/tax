package com.louis.xy.tax.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = Product.COLLECTION_NAME)
public class Product {

    public static final String COLLECTION_NAME = "product";

    @Id
    private String id;

    private String name;
    private String taxCode;
    private BigDecimal price;

}
