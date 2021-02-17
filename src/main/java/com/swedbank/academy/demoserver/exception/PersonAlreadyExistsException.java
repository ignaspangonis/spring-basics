package com.swedbank.academy.demoserver.exception;

public class PersonAlreadyExistsException extends Exception {
    public PersonAlreadyExistsException(long pid) {
        super("Person already exists! Person ID: " + pid);
    }
}
