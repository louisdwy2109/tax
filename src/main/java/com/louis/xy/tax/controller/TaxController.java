package com.louis.xy.tax.controller;

import com.louis.xy.tax.model.entity.CalculatedProduct;
import com.louis.xy.tax.model.entity.Product;
import com.louis.xy.tax.model.web.CalculatedProductWebModel;
import com.louis.xy.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    TaxService taxService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<String, String> index(){
        Map<String, String> response = new HashMap<>();

        response.put("test", "test");
        return response;
    }

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public List<CalculatedProductWebModel> addToCart(@RequestBody Product product){
        return taxService.addToCart(product);
    }



}
