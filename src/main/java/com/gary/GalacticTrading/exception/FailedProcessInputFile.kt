package com.gary.GalacticTrading.exception;

public class FailedProcessInputFile extends RuntimeException{
    public FailedProcessInputFile(String message) {
        super(message);
    }

    public FailedProcessInputFile(String message, Throwable cause) {
        super(message, cause);
    }
}
