package com.app.nailcare.exceptions;
/**
 * An exception thrown to indicate that a user is not logged in, and an operation requiring authentication was attempted.
 */
public class UserNotLoggedInException extends RuntimeException {

    /**
     * Constructs a UserNotLoggedInException with a custom error message.
     *
     * @param message The error message describing the reason for the exception.
     */
    public UserNotLoggedInException(String message){
        super(message);
    }
}




