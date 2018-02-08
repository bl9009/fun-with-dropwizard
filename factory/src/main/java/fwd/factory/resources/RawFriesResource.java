package fwd.factory.resources;

import fwd.factory.main.Factory;
import fwd.factory.main.FriesDelivery;
import fwd.factory.main.FriesOrder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/factory/fries")
@Produces(MediaType.APPLICATION_JSON)
public class RawFriesResource {

    private Factory factory;

    public RawFriesResource(Factory factory) {
        this.factory = factory;
    }

    @GET
    public FriesDelivery getFries(@QueryParam("quantity") int quantity) {
        return factory.deliver(new FriesOrder(quantity));
    }
}
