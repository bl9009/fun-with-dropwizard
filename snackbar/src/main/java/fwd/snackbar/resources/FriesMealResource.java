package fwd.snackbar.resources;

import fwd.snackbar.main.FriesMeal;
import fwd.snackbar.main.MealSize;
import fwd.snackbar.main.Topping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/snackbar/fries")
@Produces(MediaType.APPLICATION_JSON)
public class FriesMealResource {

    @GET
    public FriesMeal getFriesMeal(@QueryParam("size") MealSize size,
                                  @QueryParam("topping") Topping topping)
    {
        return new FriesMeal(size, topping);
    }
}
