package com.jussix.training.startspringboot.exception;

public class ProductException extends BaseException{

    public ProductException(String code) {
        super("product" + code);
    }

    public static ProductException notFound(){
        return new ProductException("not.found");
    }

}
