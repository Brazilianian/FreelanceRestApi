package com.mitit.exception.alreadyexists;

public abstract class AbstractAlreadyExistsException extends RuntimeException {
    public AbstractAlreadyExistsException() {
    }

    public AbstractAlreadyExistsException(String message) {
        super(message);
    }
}
