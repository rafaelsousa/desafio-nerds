package ca.renardnumerique.endpoints;

import ca.renardnumerique.persistence.domain.Telephone;
import ca.renardnumerique.persistence.repository.TelephoneRepository;
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

@Path("/telephone")
public class TelephoneEndpoint {

    @Inject
    TelephoneRepository repository;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Uni<Telephone> get(@PathParam("id") Long id) {
        return Uni.createFrom().item(repository.findById(id));
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> add(Telephone telephone) {
        return repository
                .persist(telephone)
                .onItem()
                .transform(this::generatePersistResponse);
    }

    private Response generatePersistResponse(Telephone telephone) {
        return Response.ok(telephone).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(Telephone telephone) {
        return repository
                .persist(telephone)
                .onItem()
                .transform(this::generatePersistResponse);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return Uni.createFrom()
                .item(repository.delete(id))
                .onItem()
                .transform(x -> Boolean.TRUE.equals(x) ? Response.status(200).build() : Response.notModified().build());
    }
}
