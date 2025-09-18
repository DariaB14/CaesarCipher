package com.javarush.exceptions;

public class ValidationException extends AppException{
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
