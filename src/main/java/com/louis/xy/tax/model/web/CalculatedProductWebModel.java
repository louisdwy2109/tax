package com.louis.xy.tax.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedProductWebModel {
    private String id;
    private String name;
    private String taxCode;
    private boolean refundable;
    private String productType;
    private BigDecimal price;
    private BigDecimal taxPrice;
    private BigDecimal totalPrice;
}
