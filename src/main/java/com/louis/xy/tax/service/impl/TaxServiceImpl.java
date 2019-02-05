package com.louis.xy.tax.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.louis.xy.tax.model.entity.CalculatedProduct;
import com.louis.xy.tax.model.entity.Product;
import com.louis.xy.tax.model.enums.TaxCodeEnum;
import com.louis.xy.tax.model.web.CalculatedProductWebModel;
import com.louis.xy.tax.repository.CalculatedProductRepository;
import com.louis.xy.tax.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxServiceImpl implements TaxService {

    @Autowired
    CalculatedProductRepository calculatedProductRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<CalculatedProductWebModel> addToCart(Product product) {


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

        List<CalculatedProduct> calculatedProducts = calculatedProductRepository.findAll();

        return calculatedProducts.stream()
                .filter(calculatedProductEntity -> calculatedProductEntity != null)
                .map(calculatedProductEntity -> objectMapper.convertValue(calculatedProductEntity, CalculatedProductWebModel.class))
                .collect(Collectors.toList());
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
