package com.mitit.exception.notfound;

public abstract class AbstractNotFoundException extends RuntimeException{
    public AbstractNotFoundException() {
        super();
    }

    public AbstractNotFoundException(String message) {
        super(message);
    }
}
