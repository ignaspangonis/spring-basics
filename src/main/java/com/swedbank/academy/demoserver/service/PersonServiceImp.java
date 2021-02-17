package com.swedbank.academy.demoserver.service;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Autowired                      // veiktų ir be šito rašome tik dėl readibility
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(final long pid) throws PersonNotFoundException {
        Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        return person;

    }

    @Override
    public void delete(long pid) throws PersonNotFoundException {
        Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        personRepository.deleteById(pid);
    }

    @Override
    public boolean save(Person person) {
        Optional<Person> p = personRepository.findById(person.getPid());
        if (p.isPresent())
            return false;
        else {
            personRepository.save(person);
            return true;
        }
    }

    @Override
    public boolean update(Person person) throws PersonNotFoundException {
        boolean doneUpdate = false;
        long pid = person.getPid();
        Optional<Person> p = personRepository.findById(pid);
        if (p.isPresent()) {
            personRepository.save(person);
            doneUpdate = true;
        }
        else {
            throw new PersonNotFoundException(pid);
        }
        return doneUpdate;
    }

    @Override
    public void saveAndFlush(Person person) {
        personRepository.saveAndFlush(person);

    }
}
