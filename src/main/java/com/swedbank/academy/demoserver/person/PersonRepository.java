package com.swedbank.academy.demoserver.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

// ant interfeisų anotacijos neprisideda @Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // interfeisą extendinti gali tik kitas interfeisas
    // JpaRepository turi mums naudingų metodų
    // pvz. findAll()



}
