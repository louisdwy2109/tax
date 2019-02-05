package com.louis.xy.tax.controller;

import com.louis.xy.tax.model.CalculatedProduct;
import com.louis.xy.tax.model.Product;
import com.louis.xy.tax.model.enums.TaxCodeEnum;
import com.louis.xy.tax.repository.CalculatedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    CalculatedProductRepository calculatedProductRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Map<String, String> index(){
        Map<String, String> response = new HashMap<>();

        response.put("test", "test");
        return response;
    }

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public List<CalculatedProduct> addToCart(@RequestBody Product product){
        TaxCodeEnum taxCodeEnum = TaxCodeEnum.findByTaxCode(product.getTaxCode());

        BigDecimal taxPrice = getTaxPrice(product);

        CalculatedProduct calculatedProduct =
                CalculatedProduct.builder()
                        .name(product.getName())
                        .price(product.getPrice().setScale(1, BigDecimal.ROUND_HALF_DOWN))
                        .productType(taxCodeEnum.getTaxName())
                        .refundable(taxCodeEnum.isRefundable())
                        .taxCode(taxCodeEnum.getTaxCode())
                        .taxPrice(taxPrice.setScale(1, BigDecimal.ROUND_HALF_DOWN))
                        .totalPrice(product.getPrice().add(taxPrice).setScale(1, BigDecimal.ROUND_HALF_DOWN))
                        .build();

        calculatedProductRepository.save(calculatedProduct);

        return calculatedProductRepository.findAll();

    }

    private BigDecimal getTaxPrice(Product product){

        if(TaxCodeEnum.TAX_CODE_1.getTaxCode().equals(product.getTaxCode())){
            return product.getPrice().multiply(new BigDecimal(0.1));
        }

        if(TaxCodeEnum.TAX_CODE_2.getTaxCode().equals(product.getTaxCode())){
            return product.getPrice().multiply(new BigDecimal(0.02)).add(new BigDecimal(10));
        }

        if(TaxCodeEnum.TAX_CODE_3.getTaxCode().equals(product.getTaxCode())){
            if(product.getPrice().compareTo(new BigDecimal(100)) <= 0){
                return BigDecimal.ZERO;
            }

            return product.getPrice().subtract(new BigDecimal(100)).multiply(new BigDecimal(0.01));
        }

        return BigDecimal.ZERO;
    }

}
