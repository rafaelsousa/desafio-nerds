package ca.renardnumerique.persistence.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Telephone extends PanacheEntityBase {


    @Id
    @SequenceGenerator(name = "telephoneSeq", sequenceName = "telephoneSeq_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "telephoneSeq")
    public Long phoneId;
    public String number;
    public String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId")
    public Person person;
}
