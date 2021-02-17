package com.swedbank.academy.demoserver.person.validator;

import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.person.exception.PersonPostFailedException;

public class PersonValidator implements Validator {

    @Override
    public void validate(Person person) throws PersonPostFailedException {
        if (person.getPid() < 0) throw new PersonPostFailedException("Fail");
    }
}
