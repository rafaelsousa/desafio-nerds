package ca.renardnumerique.persistence.repository;

import ca.renardnumerique.persistence.domain.Telephone;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TelephoneRepository {

    @Inject
    Mutiny.Session mutinySession;

    @Inject
    EntityManager entityManager;

    public Telephone findById(Long id) {
        return entityManager.find(Telephone.class, id);
    }


    public Uni<Telephone> persist(Telephone telephone) {
        return mutinySession.persist(telephone)
                .chain(mutinySession::flush)
                .onItem().transform(ignore -> telephone);
    }


    public Boolean delete(Long id) {
        entityManager.remove(entityManager.find(Telephone.class, id));
        return Boolean.TRUE;
    }

}
