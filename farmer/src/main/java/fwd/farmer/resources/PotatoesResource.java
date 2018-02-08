package fwd.farmer.resources;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;
import fwd.farmer.main.PotatoVendor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/farmer/potatoes")
@Produces(MediaType.APPLICATION_JSON)
public class PotatoesResource {

    private PotatoVendor producer;

    public PotatoesResource(PotatoVendor producer) {
        this.producer = producer;
    }

    @GET
    public PotatoDelivery getPotatoes(@QueryParam("quantity") int quantity) {
        PotatoDelivery delivery = producer.deliver(new PotatoOrder(quantity));

        return delivery;
    }
}