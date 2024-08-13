package com.gary.GalacticTrading.exception;

public class InvalidIntergalacticUnitException extends RuntimeException{
    public InvalidIntergalacticUnitException(String message) {
        super(message);
    }

    public InvalidIntergalacticUnitException(String message, Throwable cause) {
        super(message, cause);
    }
}
