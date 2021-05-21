package ca.renardnumerique;

import ca.renardnumerique.persistence.domain.Person;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonEndpoint {

    @Inject
    PersonRepository repository;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Uni<Person> get(@PathParam("id") Long id){
        return Uni.createFrom().item(repository.findById(id));
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> add(Person person) {
        return repository
                .persist(person)
                .onItem()
                .transform(this::generatePersistResponse);
    }

    private Response generatePersistResponse(Person person) {
        return Response.ok(person).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(Person person) {
        return repository
                .persist(person)
                .onItem()
                .transform(this::generatePersistResponse);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return Uni.createFrom()
                .item(repository.delete(id))
                .onItem()
                .transform(x -> Boolean.TRUE.equals(x)? Response.status(200).build() : Response.notModified().build());
    }
}
