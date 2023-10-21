package com.app.nailcare.exceptions;

public class AlreadyExistException extends RuntimeException {
    public <T> AlreadyExistException(Class<T> clazz, String attribute, String value){
        super(clazz.getSimpleName() + " already exists with " + attribute + ": " + value);
    }
}
