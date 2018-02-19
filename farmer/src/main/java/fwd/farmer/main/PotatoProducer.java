package fwd.farmer.main;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;

public interface PotatoProducer {

    void addOrder(PotatoOrder order);
    void produce();
    void processOrder();
    void deliver();

}
