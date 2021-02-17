package com.swedbank.academy.demoserver.person;

import com.swedbank.academy.demoserver.person.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.person.exception.PersonPostFailedException;
import com.swedbank.academy.demoserver.person.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;
    private final PersonValidator personValidator;

    @Autowired                      // veiktų ir be šito rašome tik dėl readibility
    public PersonServiceImp(PersonRepository personRepository, PersonValidator personValidator) {
        this.personRepository = personRepository;
        this.personValidator = personValidator;
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
        // ar turime? ne -> throwinam exceptiona
        personRepository.deleteById(pid);
    }

    @Override
    public void addPerson(Person person) throws PersonPostFailedException {
        personValidator.validate(person);
        personRepository.save(person);
    }
}
