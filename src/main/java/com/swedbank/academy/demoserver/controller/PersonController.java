package com.swedbank.academy.demoserver.controller;

import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.exception.PersonAlreadyExistsException;
import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getAll();

        // Žinome, kad reikia DB įrašų, galime grąžinti:
        // Atsiranda galimybė paduoti ne tik kaip
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @GetMapping("{pid}")
    public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {

        try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            //log.error("getPersonByPid", ex);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Void> deletePerson(@PathVariable("pid") Long pid) {
        try {
            personService.delete(pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException e) {
            //log.error("deletePerson", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> postPerson(@PathVariable("person") Person person) {
        try {
            personService.addPerson(person);
            return ResponseEntity.ok().build();
        } catch (PersonAlreadyExistsException e) {
            //log.error("deletePerson", e);
            return ResponseEntity.notFound().build();
        }
    }
}
