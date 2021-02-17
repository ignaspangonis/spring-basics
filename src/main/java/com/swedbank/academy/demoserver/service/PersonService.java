package com.swedbank.academy.demoserver.service;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.person.Person;

import java.util.List;

public interface PersonService {
    // apsirašome per kur jungsis Controlleris
    // kokius metodus galės matyti Controlleris?

    List<Person> getAll();

    /** READ*/
    public Person getById(long pid) throws PersonNotFoundException;
    /** DELETE*/
    public void delete(long pid) throws PersonNotFoundException;
    /** CREATE*/
    public boolean save(Person person);
    /** UPDATE */
    public boolean update(Person person)throws PersonNotFoundException;

    public void saveAndFlush(Person person);
}
