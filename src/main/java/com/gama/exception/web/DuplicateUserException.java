package com.gama.exception.web;

public class DuplicateUserException extends  Exception {

    public DuplicateUserException(String message) {
        super(message);
    }
}
