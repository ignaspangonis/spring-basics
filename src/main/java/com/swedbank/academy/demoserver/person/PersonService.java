package com.swedbank.academy.demoserver.person;

import com.swedbank.academy.demoserver.person.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.person.exception.PersonPostFailedException;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    // apsirašome per kur jungsis Controlleris
    // kokius metodus galės matyti Controlleris?

    List<Person> getAll();

    Person getById(final long pid) throws PersonNotFoundException;

    void delete(long pid) throws PersonNotFoundException;

    public void addPerson(Person person) throws PersonPostFailedException;
}
