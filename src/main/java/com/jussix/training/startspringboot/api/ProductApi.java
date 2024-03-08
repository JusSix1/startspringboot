package com.jussix.training.startspringboot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jussix.training.startspringboot.business.ProductBusiness;
import com.jussix.training.startspringboot.exception.ProductException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/product")
public class ProductApi {

    private final ProductBusiness business;

    public ProductApi(ProductBusiness business){
        this.business = business;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<String> getProductByID(@PathVariable("id") String id) throws ProductException {
        String response = business.getProductById(id);
        return ResponseEntity.ok(response);
    }
    

}
