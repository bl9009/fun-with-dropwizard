package fwd.farmer.main;

import fwd.common.kv.ConnectionException;
import fwd.common.kv.RedisStore;
import fwd.farmer.resources.PotatoesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmerApplication extends Application<FarmerConfiguration> {

    private Farmer farmer;

    private Logger log;

    public static void main(String[] args) throws Exception {
        new FarmerApplication().run(args);
    }

    public FarmerApplication() {
        log = LoggerFactory.getLogger(FarmerApplication.class);
    }

    @Override
    public void initialize(Bootstrap<FarmerConfiguration> bootstrap)
    {
        // ;
    }


    @Override
    public void run(FarmerConfiguration configuration, Environment environment)
    {
        RedisStore store;

        try {
            store = new RedisStore("fwd_redis_1");
        }
        catch (ConnectionException e) {
            log.error("could not connect to redis");

            return;
        }

        FarmerWarehouse warehouse = new FarmerWarehouse(store);

        farmer = new Farmer(warehouse, configuration.getProductionRate());

        final FarmerRunner runner = new FarmerRunner(farmer);

        final PotatoesResource resource = new PotatoesResource(farmer);

        environment.jersey().register(resource);

        environment.lifecycle().manage(runner);
    }
}
