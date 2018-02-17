package fwd.farmer.main;

import fwd.common.main.*;

public class Farmer implements PotatoVendor {

    private FarmerKvObject farmerObj;

    private int productionRate;

    public Farmer(FarmerKvObject farmerObj, int productionRate) {
        this.farmerObj = farmerObj;

        this.productionRate = productionRate;
    }

    @Override
    public synchronized PotatoDelivery deliver(PotatoOrder order) {
        int quantity = order.getQuantity();

        boolean retry = true;

        while (retry) {
            farmerObj.watch();

            int stock = farmerObj.getStock();

            if (stock < quantity) {
                quantity = 0;
            }
            else {
                farmerObj.multi();

                farmerObj.decreaseStock(stock);

                try {
                    farmerObj.exec();

                    retry = false;
                } catch (TransactionFailedException e) {
                    retry = true;
                }
            }
        }

        return new PotatoDelivery(quantity);
    }

    public void produce() {
        farmerObj.increaseStock(productionRate);
    }
}
