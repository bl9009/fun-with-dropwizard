package fwd.farmer.main;

import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FarmerRunner implements Managed {

    private ProductionRunnable productionJob;
    private OrderRunnable orderJob;

    private Thread productionThread;
    private Thread orderThread;

    private Logger logger;

    public FarmerRunner(Farmer farmer) {

        this.productionJob = new ProductionRunnable(farmer);
        this.orderJob = new OrderRunnable(farmer);

        productionThread = new Thread(productionJob);
        orderThread = new Thread(orderJob);

        logger = LoggerFactory.getLogger(FarmerRunner.class);
    }

    @Override
    public void start() {
        logger.info("Spawning production thread.");
        productionThread.start();

        logger.info("Spawning order handling thread.");
        orderThread.start();
    }

    @Override
    public void stop() {
        logger.info("Stopping production thread.");
        productionJob.terminate();

        logger.info("Stopping order handling thread.");
        orderJob.terminate();

        try {
            productionThread.join();
            orderThread.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
