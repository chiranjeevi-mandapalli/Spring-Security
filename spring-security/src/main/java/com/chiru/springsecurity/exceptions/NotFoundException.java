package com.chiru.springsecurity.exceptions;

/**
 * @author Chiranjeevi
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
