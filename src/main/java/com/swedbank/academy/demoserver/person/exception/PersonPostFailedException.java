package com.swedbank.academy.demoserver.person.exception;

import com.swedbank.academy.demoserver.person.Person;

public class PersonPostFailedException extends Exception {
    public PersonPostFailedException(String message) {
        super("Person post failed! Reason: " + message);
    }
}
