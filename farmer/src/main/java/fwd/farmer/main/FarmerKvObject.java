package fwd.farmer.main;

import fwd.common.main.KeyException;
import fwd.common.main.KvObject;
import fwd.common.main.KvStore;

public class FarmerKvObject extends KvObject {

    private String stockKey;

    public FarmerKvObject(KvStore store) {
        super(store);

        stockKey = "farmer:stock";

        try {
            getTransaction().getInt(stockKey);
        } catch (KeyException e)
        {
            setStock(0);
        }
    }

    public void watch() {
        super.watch(stockKey);
    }

    public int getStock() {
        try {
            return getTransaction().getInt(stockKey);
        }
        catch (KeyException e) {
            return 0;
        }
    }

    public void setStock(int stock) {
        getTransaction().setInt(stockKey, stock);
    }

    public void increaseStock(int quantity) {
        getTransaction().incrBy(stockKey, quantity);
    }

    public void decreaseStock(int quantity) {
        getTransaction().decrBy(stockKey, quantity);
    }
}
