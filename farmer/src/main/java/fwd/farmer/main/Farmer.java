package fwd.farmer.main;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;

public class Farmer implements PotatoVendor {

    private int stock;
    private int productionRate;

    public Farmer(int productionRate) {
        this.productionRate = productionRate;
    }

    @Override
    public synchronized PotatoDelivery deliver(PotatoOrder order) {

        while (stock < order.getQuantity())
        {
            produce();
        }

        int quantity = order.getQuantity();

        stock -= quantity;

        return new PotatoDelivery(quantity);
    }

    private void produce() {
        stock += productionRate;
    }
}
