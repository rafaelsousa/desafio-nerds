package ca.renardnumerique.persistence.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Address extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "addressSeq", sequenceName = "addressSeq_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "addressSeq")
    public Long addressId;
    public String type;
    public String address;
    public Long number;
    public String suite;
    public String state;
    public String country;
    public String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    public Person person;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return addressId.equals(address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
