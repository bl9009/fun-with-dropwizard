package fwd.factory.main;

import fwd.factory.resources.RawFriesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class FactoryApplication extends Application<FactoryConfiguration> {

    private Factory factory;

    public static void main(String[] args) throws Exception {
        new FactoryApplication().run(args);
    }

    @Override
    public void run(FactoryConfiguration configuration, Environment environment) {
        PotatoResource potatoVendor = new PotatoResource();

        factory = new Factory(configuration.getProductionRate(), configuration.getFriesPerPotato(), potatoVendor);

        environment.jersey().register(new RawFriesResource(factory));
    }
}
