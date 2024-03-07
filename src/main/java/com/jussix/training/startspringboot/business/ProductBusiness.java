package com.jussix.training.startspringboot.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.jussix.training.startspringboot.exception.ProductException;

@Service
public class ProductBusiness {

    public String getProductById(String id) throws ProductException{
        if(Objects.equals("1234", id)){
            throw ProductException.notFound();
        }
        return id;
    }

}
