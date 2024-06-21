package com.gary.GalacticTrading.exception;

public class NoMetalValueDefinitionsFoundException extends RuntimeException {
    public NoMetalValueDefinitionsFoundException(String message) {
        super(message);
    }

    public NoMetalValueDefinitionsFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
