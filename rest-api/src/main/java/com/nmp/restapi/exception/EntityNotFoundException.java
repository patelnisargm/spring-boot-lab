package com.nmp.restapi.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, Class<?> entity) {
        super("The " + entity.getName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }
}
