package com.core.exceptions;

public class PropertyFileException extends CoreException {
    public PropertyFileException(String message) {
        super(message);
    }

    public PropertyFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
