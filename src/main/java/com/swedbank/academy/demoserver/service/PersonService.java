package com.swedbank.academy.demoserver.service;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.exception.PersonAlreadyExistsException;
import com.swedbank.academy.demoserver.person.Person;

import java.util.List;

public interface PersonService {
    // apsirašome per kur jungsis Controlleris
    // kokius metodus galės matyti Controlleris?

    List<Person> getAll();

    Person getById(final long pid) throws PersonNotFoundException;

    void delete(long pid) throws PersonNotFoundException;

    public void addPerson(Person person) throws PersonAlreadyExistsException;
}
