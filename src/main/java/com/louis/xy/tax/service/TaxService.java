package com.louis.xy.tax.service;

import com.louis.xy.tax.model.entity.Product;
import com.louis.xy.tax.model.web.CalculatedProductWebModel;

import java.util.List;

public interface TaxService {
    List<CalculatedProductWebModel> addToCart(Product product);
}
