package ca.renardnumerique.endpoints;

import ca.renardnumerique.persistence.domain.Address;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestPath;
import org.modelmapper.ModelMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.*;


@Path("/address")
public class AddressEndpoint {


    @GET
    public Uni<List<Address>> get(){
        return Address.listAll();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Uni<Address> getSingle(@RestPath("id") Long id) {
        return Address.findById(id);
    }


    @POST
    public Uni<Response> add(Address address) {
        if(address!=null){
            throw new WebApplicationException("Entity was null", 422);

        }
        return Panache.withTransaction(address::persist)
                .replaceWith(
                        Response.ok().status(CREATED)::build
                );
    }

    @PUT
    public Uni<Response> update(@RestPath Long id, Address addressSrc) {
        if (addressSrc == null || addressSrc.addressId == null) {
            throw new WebApplicationException("Address id was not set on request.", 422);
        }
        return Panache
                .withTransaction(() -> Address.<Address> findById(addressSrc.addressId)
                    .onItem().ifNotNull().invoke((addressDest) -> {
                        var mapper = new ModelMapper();
                        mapper.map(addressSrc,addressDest);
                    })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    @ReactiveTransactional
    public Uni<Response> delete(@RestPath("id") Long id) {
        return Panache.withTransaction(() -> Address.deleteById(id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());

    }
}
