package com.mitit.exception.notfound;

public class CategoryNotFoundException extends AbstractNotFoundException {
    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
