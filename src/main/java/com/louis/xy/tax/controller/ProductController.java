package com.louis.xy.tax.controller;


import com.louis.xy.tax.model.Product;
import com.louis.xy.tax.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Product insert(@RequestBody Product product){
        productRepository.save(product);
        return product;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Product get(@RequestParam String id){
        return productRepository.findById(id);
    }

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
