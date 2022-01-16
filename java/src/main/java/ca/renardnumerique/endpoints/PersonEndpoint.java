package ca.renardnumerique.endpoints;

import ca.renardnumerique.persistence.domain.Person;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.*;

@Path("/person")
public class PersonEndpoint {
    @GET
    public Uni<List<Person>> get(){
        return Person.listAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Uni<Person> getSingle(@RestPath("id") Long id) {
        return Person.findById(id);
    }


    @POST
    public Uni<Response> add(Person Person) {
        if(Person!=null){
            throw new WebApplicationException("Entity was null", 422);

        }
        return Panache.withTransaction(Person::persist)
                .replaceWith(
                        Response.ok().status(CREATED)::build
                );
    }

    @PUT
    public Uni<Response> update(@RestPath Long id, Person personSrc) {
        if (personSrc == null || personSrc.personId == null) {
            throw new WebApplicationException("Person id was not set on request.", 422);
        }
        return Panache
                .withTransaction(() -> Person.<Person> findById(personSrc.personId)
                        .onItem().ifNotNull().invoke((PersonDest) -> {
                            var mapper = new ModelMapper();
                            mapper.map(personSrc,PersonDest);
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    @ReactiveTransactional
    public Uni<Response> delete(@RestPath("id") Long id) {
        return Panache.withTransaction(() -> Person.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());

    }
}
