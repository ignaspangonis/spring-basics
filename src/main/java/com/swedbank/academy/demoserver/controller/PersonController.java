package com.swedbank.academy.demoserver.controller;

import com.swedbank.academy.demoserver.exception.GroupNotFoundException;
import com.swedbank.academy.demoserver.exception.PersonNotFoundException;
import com.swedbank.academy.demoserver.person.Group;
import com.swedbank.academy.demoserver.person.Person;
import com.swedbank.academy.demoserver.service.GroupService;
import com.swedbank.academy.demoserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;
    private GroupService groupService;

    @Autowired
    public PersonController(PersonService personService, GroupService groupService) {
        this.personService = personService;
        this.groupService = groupService;
    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getAll();

        // Žinome, kad reikia DB įrašų, galime grąžinti:
        // Atsiranda galimybė paduoti ne tik kaip
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    //** CREATE */
    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        boolean success = personService.save(person);
        if (!success) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/person/{id}").buildAndExpand(person.getPid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //** READ */
    @GetMapping("{pid}")
    public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {

        Person person = personService.getById(pid);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    //** UPDATE */
    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.update(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    //** DELETE */
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

    @GetMapping("{pid}/groups")
    public ResponseEntity<Collection<Group>> getPersonGroups(@PathVariable("pid") Long pid) {

        try {
            Person person = personService.getById(pid);
            return new ResponseEntity<Collection<Group>>(person.getGroups(), HttpStatus.OK);
        } catch (PersonNotFoundException ex) {
            //log.error("getPersonGroups", ex);
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{pid}/groups/{id}")
    public ResponseEntity<?> setGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {

        try {
            Person person = personService.getById(pid);
            Group group = groupService.findById(id);
            Set<Group> groups = person.getGroups();
            groups.add(group);
            person.setGroups(groups);
            personService.saveAndFlush(person);
            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException | PersonNotFoundException ex) {
            //log.error("setGroup", ex);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{pid}/groups/{id}")
    public ResponseEntity<?> removeFromGroup(@PathVariable("pid") long pid, @PathVariable("id") long id) {
        try {
            Person person = personService.getById(pid);
            Group group = groupService.findById(id);
            Set<Group> groups = person.getGroups();
            if (groups.contains(group)) {
                groups.remove(group);
                person.setGroups(groups);
                personService.saveAndFlush(person);
            }

            return ResponseEntity.ok().build();
        } catch (GroupNotFoundException | PersonNotFoundException ex) {
            //log.error("setGroup", ex);
            return ResponseEntity.notFound().build();
        }
    }
}
