package fwd.farmer.main;

import fwd.common.main.KvStore;
import fwd.common.main.KeyException;
import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;

public class Farmer implements PotatoVendor {

    private int productionRate;

    private KvStore store;

    public Farmer(KvStore store, int productionRate) {
        this.store = store;

        this.productionRate = productionRate;
    }

    @Override
    public synchronized PotatoDelivery deliver(PotatoOrder order) {
        int stock = getStock();

        while (stock < order.getQuantity())
        {
            produce();
        }

        int quantity = order.getQuantity();

        setStock(stock - quantity);

        return new PotatoDelivery(quantity);
    }

    private void produce() {
        int stock = getStock();

        setStock(stock + productionRate);
    }

    public int getStock() {
        try {
            return store.getInt("stock");
        }
        catch (KeyException e) {
            return 0;
        }
    }

    public void setStock(int stock) {
        store.setInt("stock", stock);
    }
}
