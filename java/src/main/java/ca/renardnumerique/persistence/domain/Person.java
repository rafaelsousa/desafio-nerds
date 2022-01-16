package ca.renardnumerique.persistence.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
public class Person extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "personSeq_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "personSeq")
    public Long personId;
    public String name;
    public String email;

    @OneToMany(mappedBy = "person")
    public Set<Address> addresses;

    @OneToMany(mappedBy = "person")
    public Set<Telephone> telephones;
    public LocalDate birthDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return personId.equals(person.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}



