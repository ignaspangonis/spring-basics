package com.swedbank.academy.demoserver.person.validator;

import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.person.exception.PersonPostFailedException;

public interface Validator {
    void validate(Person person) throws PersonPostFailedException;
}
