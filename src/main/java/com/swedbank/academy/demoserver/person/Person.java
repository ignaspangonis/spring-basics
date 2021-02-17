package com.swedbank.academy.demoserver.person;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                     // parodo, kad tai entity
@Table(name = "person")     // H2 DB nėra case-sensitive, todėl galime taip
@Data                       // Lambokui (leidžia handlint) sakome, kad čia duomenų klasė
@NoArgsConstructor          // Lambokui - galim turėti konstruktorių be parametrų
                            // Lambok išvengia getterius setterius
                            // nebereikia getterių/setterių

public class Person {

    @Id
    @NotNull
    private long pid;

    @NotNull
    @NotBlank
    @Column(name="first_name")  // if the field name is different from DB attribute name, we tell it here
    private String name;

    @Column(name="middle_name")
    private String middleName;

    @NotNull
    @NotBlank
    @Column(name="last_name")
    private String lastName;

    private String email;

    private String phone;
}
