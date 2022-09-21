package com.core.exceptions;

public class BrowserInitializationException extends CoreException {
    public BrowserInitializationException(String message) {
        super(message);
    }

    public BrowserInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
