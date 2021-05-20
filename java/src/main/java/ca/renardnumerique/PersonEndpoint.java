package ca.renardnumerique;

import ca.renardnumerique.persistence.domain.Person;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class PersonEndpoint {

    @Inject
    PersonResource resource;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Person get(@PathParam("id") Long id){
        Person person = resource.get(id);
        if (person == null) {
            throw new WebApplicationException(404);
        }
        return person;
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") Long id, Person personToSave) {
        if (resource.get(id) == null) {
            Person person = resource.update(id, personToSave);
            return Response.status(204).build();
        }
        Person person = resource.update(id, personToSave);
        return Response.status(201).build();
    }

    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Person person) {
        resource.add(person);
        return Response.status(200).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        if (!resource.delete(id)) {
            throw new WebApplicationException(404);
        }
        return Response.status(200).build();
    }
}
