package fwd.farmer.main;

import fwd.common.kv.KeyException;
import fwd.common.kv.KvObject;
import fwd.common.kv.KvStore;
import fwd.common.kv.TransactionFailedException;

public class FarmerWarehouse extends KvObject {

    private int capacity;

    private String stockKey;

    public FarmerWarehouse(KvStore store, int capacity) {
        super(store);

        this.capacity = capacity;

        stockKey = "farmer:warehouse.stock";

        try {
            getTransaction().getInt(stockKey);
        } catch (KeyException e)
        {
            getTransaction().setInt(stockKey, 0);
        }
    }

    public FarmerWarehouse(KvStore store) {
        this(store, 123456789);
    }

    public int getStockSize() {
        try {
            return getTransaction().getInt(stockKey);
        }
        catch (KeyException e) {
            return 0;
        }
    }

    public void fetch(int quantity) throws OutOfStockException {
        boolean retry;

        do {
            getTransaction().watch(stockKey);

            if (getStockSize() < quantity) {
                getTransaction().unwatch();

                throw new OutOfStockException();
            }

            getTransaction().multi();

            getTransaction().decreaseBy(stockKey, quantity);

            try {
                getTransaction().exec();

                retry = false;
            } catch (TransactionFailedException e) {
                retry = true;
            }
        } while (retry);
    }

    public void put(int quantity) throws OutOfCapacityException {
        boolean retry;

        do {
            getTransaction().watch(stockKey);

            if ((getStockSize() + quantity) > capacity) {
                getTransaction().unwatch();

                throw new OutOfCapacityException();
            }

            getTransaction().multi();

            getTransaction().increaseBy(stockKey, quantity);

            try {
                getTransaction().exec();

                retry = false;
            } catch (TransactionFailedException e) {
                retry = true;
            }
        } while (retry);
    }
}
