package ca.renardnumerique.persistence.repository;

import ca.renardnumerique.persistence.domain.Address;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AddressRepository {

    @Inject
    Mutiny.Session mutinySession;

    @Inject
    EntityManager entityManager;

    public Address findById(Long id) {
        return entityManager.find(Address.class, id);
    }


    public Uni<Address> persist(Address address) {
        return mutinySession.persist(address)
                .chain(mutinySession::flush)
                .onItem().transform(ignore -> address);
    }


    public Boolean delete(Long id) {
        entityManager.remove(entityManager.find(Address.class, id));
        return Boolean.TRUE;
    }

}
