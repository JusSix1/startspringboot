package com.jussix.training.startspringboot.exception;

public class UserException  extends BaseException{

    public UserException(String code) {
        super("user." + code);
    }


    //Register
    public static UserException registerNull(){
        return new UserException("register request.null");
    }

    public static UserException emailNull(){
        return new UserException("register.email.null");
    }

    //Create
    public static UserException createEmailNull(){
        return new UserException("create.email.null");
    }

    public static UserException createEmailDuplicated(){
        return new UserException("create.email.duplicated");
    }

    public static UserException createPasswordNull(){
        return new UserException("create.password.null");
    }

    public static UserException createNameNUll(){
        return new UserException("create.name.null");
    }

    //Login
    public static UserException loginFailEmailNotFound(){
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect(){
        return new UserException("login.fail");
    }
    public static UserException unauthorized(){
        return new UserException("unauthorized");
    }

    //Update
    public static UserException notFound(){
        return new UserException("user.not.found");
    }

}
