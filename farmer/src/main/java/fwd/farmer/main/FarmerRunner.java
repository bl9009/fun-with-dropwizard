package fwd.farmer.main;

public class FarmerRunner {

    private ProductionRunnable productionJob;
    private OrderRunnable orderJob;

    private Thread productionThread;
    private Thread orderThread;

    public FarmerRunner(Farmer farmer) {

        this.productionJob = new ProductionRunnable(farmer);
        this.orderJob = new OrderRunnable(farmer);

        productionThread = new Thread(productionJob);
        orderThread = new Thread(orderJob);
    }

    public void run()
    {
        productionThread.start();
        orderThread.start();
    }

    public void stop()
    {
        productionJob.terminate();
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
