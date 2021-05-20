package ca.renardnumerique;

import ca.renardnumerique.PersonRepository;
import ca.renardnumerique.persistence.domain.Person;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;

public interface PersonResource extends PanacheRepositoryResource<PersonRepository, Person,Long> {
}
