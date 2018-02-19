package fwd.farmer.main;

public class ProductionRunnable implements Runnable {

    private Farmer farmer;

    private boolean terminating;

    public ProductionRunnable(Farmer farmer) {
        this.farmer = farmer;
    }

    public void terminate() {
        terminating = true;
    }

    @Override
    public void run() {
        while (!terminating) {
            farmer.produce();

            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
