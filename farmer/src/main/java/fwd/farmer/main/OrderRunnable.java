package fwd.farmer.main;

public class OrderRunnable implements Runnable {

    private Farmer farmer;

    private boolean terminating;

    public OrderRunnable(Farmer farmer) {
        this.farmer = farmer;
    }

    public void terminate() {
        terminating = true;
    }

    @Override
    public void run() {
        while (!terminating) {
            farmer.processOrder();

            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
