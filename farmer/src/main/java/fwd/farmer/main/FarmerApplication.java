package fwd.farmer.main;

import fwd.common.main.ConnectionException;
import fwd.common.main.RedisStore;
import fwd.farmer.resources.PotatoesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FarmerApplication extends Application<FarmerConfiguration> {

    private Farmer farmer;

    public static void main(String[] args) throws Exception {
        new FarmerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<FarmerConfiguration> bootstrap)
    {
        // ;
    }


    @Override
    public void run(FarmerConfiguration configuration, Environment environment)
    {
        RedisStore store = new RedisStore();

        try {
            store.connect("localhost");
        }
        catch (ConnectionException e) {
            return;
        }

        farmer = new Farmer(store, configuration.getProductionRate());

        final PotatoesResource resource = new PotatoesResource(farmer);

        environment.jersey().register(resource);
    }
}
