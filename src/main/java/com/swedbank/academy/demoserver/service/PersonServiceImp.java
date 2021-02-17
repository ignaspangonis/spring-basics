package com.swedbank.academy.demoserver.service;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.exception.PersonAlreadyExistsException;
import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
    }

    @Override
    public void delete(long pid) throws PersonNotFoundException {
        personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        // ar turime? ne -> throwinam exceptiona
        personRepository.deleteById(pid);
    }

    @Override
    public void addPerson(Person person) throws PersonAlreadyExistsException {
        if (personRepository.existsById(person.getPid()) ||
                personRepository.findPersonByEmail(person.getEmail()).isPresent()) {
            throw new PersonAlreadyExistsException(person.getPid());
        } else {
            personRepository.save(person);
        }
    }

    @Override
    public void updatePerson(Person newPerson, long pid) throws PersonNotFoundException {
        personRepository.findById(pid).map(person -> {
            person.setName(newPerson.getName());
            person.setLastName(newPerson.getLastName());
            person.setMiddleName(newPerson.getMiddleName());
            person.setEmail(newPerson.getEmail());
            person.setPhone(newPerson.getPhone());
            return personRepository.save(person);
        }).orElseThrow(() -> new PersonNotFoundException(pid));
    }
}
