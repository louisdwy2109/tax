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
public class ProductWebModel {
    private String id;
    private String name;
    private String taxCode;
    private BigDecimal price;
}
