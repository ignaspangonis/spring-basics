package com.swedbank.academy.demoserver.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "person_group", joinColumns = @JoinColumn(name = "pid", referencedColumnName = "pid"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    @JsonIgnore // nurodymas, kai generuoja REST endpointa, kad nebutu rekursinio rysio, pateikiame tik grupes
    // butu galima deti persona ir grupes, bet sunku istraukti duomenis - juos agreguojame ir graziname 1 irase
    @EqualsAndHashCode.Exclude
    private Set<Group> groups;
}
