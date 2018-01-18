package com.oddle.app.exception;

/**
 * Created by tao.tran on 12/14/17.
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException() {
        this("An entity with same Id has already existed!");
    }

    public EntityExistException(String message) {
        super(message);
    }
}
