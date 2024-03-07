package com.jussix.training.startspringboot.exception;

public abstract class BaseException extends Exception{

    public BaseException(String code){
        super(code);
    }

}
