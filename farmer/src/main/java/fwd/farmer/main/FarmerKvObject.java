package fwd.farmer.main;

import fwd.common.main.KeyException;
import fwd.common.main.KvObject;
import fwd.common.main.KvStore;

public class FarmerKvObject extends KvObject {

    private String stockKey;

    public FarmerKvObject(KvStore store) {
        super(store);

        stockKey = "farmer:stock";
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
}
