package ca.renardnumerique.endpoints;

import ca.renardnumerique.persistence.domain.Telephone;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.*;

@Path("/telephone")
public class TelephoneEndpoint {
    @GET
    public Uni<List<Telephone>> get(){
        return Telephone.listAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Uni<Telephone> getSingle(@RestPath("id") Long id) {
        return Telephone.findById(id);
    }


    @POST
    public Uni<Response> add(Telephone Telephone) {
        if(Telephone!=null){
            throw new WebApplicationException("Entity was null", 422);

        }
        return Panache.withTransaction(Telephone::persist)
                .replaceWith(
                        Response.ok().status(CREATED)::build
                );
    }

    @PUT
    public Uni<Response> update(@RestPath Long id, Telephone telephoneSrc) {
        if (telephoneSrc == null || telephoneSrc.phoneId == null) {
            throw new WebApplicationException("Telephone id was not set on request.", 422);
        }
        return Panache
                .withTransaction(() -> Telephone.<Telephone> findById(telephoneSrc.phoneId)
                        .onItem().ifNotNull().invoke((TelephoneDest) -> {
                            var mapper = new ModelMapper();
                            mapper.map(telephoneSrc,TelephoneDest);
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    @ReactiveTransactional
    public Uni<Response> delete(@RestPath("id") Long id) {
        return Panache.withTransaction(() -> Telephone.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());

    }
}
