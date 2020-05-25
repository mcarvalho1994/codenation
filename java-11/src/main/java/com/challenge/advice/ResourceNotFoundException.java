package com.challenge.advice;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName) {
        super("Resource: " + resourceName + "not found!");
    }
}
