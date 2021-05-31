package ca.renardnumerique.persistence.repository;

import ca.renardnumerique.persistence.domain.Person;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PersonRepository {

    @Inject
    Mutiny.Session mutinySession;

    @Inject
    EntityManager entityManager;

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }


    public Uni<Person> persist(Person person) {
        return mutinySession.persist(person)
                .chain(mutinySession::flush)
                .onItem().transform(ignore -> person);
    }


    public Boolean delete(Long id) {
        entityManager.remove(entityManager.find(Person.class, id));
        return Boolean.TRUE;
    }

}
