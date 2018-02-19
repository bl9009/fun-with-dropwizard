package fwd.farmer.resources;

import fwd.common.main.PotatoOrder;
import fwd.farmer.main.PotatoProducer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/farmer")
@Produces(MediaType.APPLICATION_JSON)
public class PotatoesResource {

    private PotatoProducer producer;

    public PotatoesResource(PotatoProducer producer) {
        this.producer = producer;
    }

    @POST
    @Path("/orders")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPotatoes(PotatoOrder order) {
        producer.addOrder(order);

        return Response.status(201).build();
    }
}
