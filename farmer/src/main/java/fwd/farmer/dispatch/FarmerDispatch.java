package fwd.farmer.dispatch;

import fwd.common.main.PotatoDelivery;

public interface FarmerDispatch {

    void dispatch(PotatoDelivery delivery) throws DispatchFailedException;
}
