package fwd.factory.main;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;

public interface PotatoVendor {

    PotatoDelivery purchase(PotatoOrder order);
}
