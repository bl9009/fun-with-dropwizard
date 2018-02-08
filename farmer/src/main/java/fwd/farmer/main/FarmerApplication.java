package fwd.farmer.main;

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
        farmer = new Farmer(configuration.getProductionRate());

        final PotatoesResource resource = new PotatoesResource(farmer);

        environment.jersey().register(resource);
    }
}
