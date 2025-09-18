package com.javarush.exceptions;

public class CipherException extends AppException{
    public CipherException(String message) {
        super(message);
    }
    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }
}
