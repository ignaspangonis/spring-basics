package com.swedbank.academy.demoserver.repository;

import com.swedbank.academy.demoserver.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// ant interfeisų anotacijos neprisideda @Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // interfeisą extendinti gali tik kitas interfeisas
    // JpaRepository turi mums naudingų metodų
    // pvz. findAll()

    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    Optional<Person> findPersonByEmail(String email);
}
