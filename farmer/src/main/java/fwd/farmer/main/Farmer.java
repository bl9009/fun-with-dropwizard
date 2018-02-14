package fwd.farmer.main;

import fwd.common.main.*;

public class Farmer implements PotatoVendor {

    private int productionRate;

    private KvStore store;

    public Farmer(KvStore store, int productionRate) {
        this.store = store;

        this.productionRate = productionRate;
    }

    @Override
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
    }

    private int produce() {
        return productionRate;
    }

    public int getStock(KvTransaction transaction) {
        try {
            return transaction.getInt("stock");
        }
        catch (KeyException e) {
            return 0;
        }
    }

    public void setStock(KvTransaction transaction, int stock) {
        transaction.setInt("stock", stock);
    }
}
