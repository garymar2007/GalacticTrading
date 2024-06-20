package com.gary.GalacticTrading.exception;

public class InvalidMetalValueDefinitionException extends RuntimeException{
    public InvalidMetalValueDefinitionException(String message) {
        super(message);
    }

    public InvalidMetalValueDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
