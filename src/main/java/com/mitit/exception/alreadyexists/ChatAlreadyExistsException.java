package com.mitit.exception.alreadyexists;

public class ChatAlreadyExistsException extends AbstractAlreadyExistsException {
    public ChatAlreadyExistsException() {
    }

    public ChatAlreadyExistsException(String message) {
        super(message);
    }
}
