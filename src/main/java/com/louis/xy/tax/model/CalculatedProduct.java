package com.louis.xy.tax.model;

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
@Document(collection = CalculatedProduct.COLLECTION_NAME)
public class CalculatedProduct {

    public static final String COLLECTION_NAME = "calculated_product";

    @Id
    private String id;

    private String name;
    private String taxCode;
    private boolean refundable;
    private String productType;
    private BigDecimal price;
    private BigDecimal taxPrice;
    private BigDecimal totalPrice;
}
