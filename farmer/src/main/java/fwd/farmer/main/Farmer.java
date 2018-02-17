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

        farmerObj.watch();

        int stock = farmerObj.getStock();

        if (stock < quantity)
        {
            quantity = 0;
        }
        else {
            stock -= quantity;

            farmerObj.multi();

            farmerObj.setStock(stock);

            farmerObj.exec();
        }

        return new PotatoDelivery(quantity);
    }

    public void produce() {
        farmerObj.watch();

        int stock = farmerObj.getStock();

        stock += productionRate;

        farmerObj.multi();

        farmerObj.setStock(stock);

        farmerObj.exec();
    }
}
