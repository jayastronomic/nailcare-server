package com.app.nailcare.exceptions;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException(String message){
        super(message);
    }
}
