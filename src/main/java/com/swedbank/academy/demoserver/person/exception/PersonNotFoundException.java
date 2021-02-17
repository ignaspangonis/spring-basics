package com.swedbank.academy.demoserver.person.exception;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(long id) {
        super("Person not found with id: " + id);
    }
}
