package com.sh.petking.ask.model.exception;

public class AskException extends RuntimeException {
    public AskException() {
    }

    public AskException(String message) {
        super(message);
    }

    public AskException(String message, Throwable cause) {
        super(message, cause);
    }

    public AskException(Throwable cause) {
        super(cause);
    }

    public AskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
