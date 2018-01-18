package com.oddle.app.exception;

/**
 * Created by tao.tran on 12/14/17.
 */
public class InvalidEntityIdException extends RuntimeException {

    public InvalidEntityIdException() {
        this("Invalid identifier for Entity");
    }

    public InvalidEntityIdException(String message) {
        super(message);
    }
}
