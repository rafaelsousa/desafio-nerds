package ca.renardnumerique;

import ca.renardnumerique.persistence.domain.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class PersonRepository implements PanacheRepositoryBase<Person, Long> {

    @Inject
    EntityManager em;

    @Transactional
    public Uni<Void> save(Person person){
       em.persist(person);
       return Uni.createFrom().nullItem();
    }


}
