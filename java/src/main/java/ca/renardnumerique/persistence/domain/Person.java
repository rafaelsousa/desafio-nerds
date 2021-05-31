package ca.renardnumerique.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "personSeq_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "personSeq")
    private Long personId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "person")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "person")
    private Set<Telephone> telephones;


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private LocalDate birthDate;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPersonId().equals(person.getPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId());
    }
}



