package com.gary.GalacticTrading.exception;

public class NoInterGalacticUnitDefinitionsFoundException extends RuntimeException{
    public NoInterGalacticUnitDefinitionsFoundException(String message) {
        super(message);
    }

    public NoInterGalacticUnitDefinitionsFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
