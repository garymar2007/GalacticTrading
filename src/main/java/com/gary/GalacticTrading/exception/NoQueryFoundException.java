package com.gary.GalacticTrading.exception;

public class NoQueryFoundException extends RuntimeException{
    public NoQueryFoundException(String message) {
        super(message);
    }

    public NoQueryFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
