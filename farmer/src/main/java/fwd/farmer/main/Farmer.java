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
            stock += productionRate;
        }

        stock -= quantity;

        farmerObj.multi();

        farmerObj.setStock(stock);

        farmerObj.exec();

        return new PotatoDelivery(quantity);
    }

    /*@Override
    public synchronized PotatoDelivery deliver(PotatoOrder order) {
        KvTransaction transaction = store.initTransaction();

        transaction.watch("stock");

        int stock = getStock(transaction);

        while (stock < order.getQuantity())
        {
            stock += produce();
        }

        int quantity = order.getQuantity();

        do {
            transaction.multi();

            setStock(transaction, stock - quantity);
        } while (transaction.exec());

        return new PotatoDelivery(quantity);
    }*/

    private int produce() {
        return productionRate;
    }
}
