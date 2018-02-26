package fwd.farmer.fulfillment;

import fwd.common.main.PotatoOrder;

public interface FarmerFulfillment {

    void receivedOrder(PotatoOrder order);
    PotatoOrder processNextOrder() throws NoOrdersToProcessException;
    void orderCompleted(PotatoOrder order);

    boolean ordersAvailable();
}
