package fwd.factory.main;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;

public class PotatoResource implements PotatoVendor {


    @Override
    public PotatoDelivery purchase(PotatoOrder order) {
        return new PotatoDelivery("fwd_factory_1", order.getQuantity());
    }
}
