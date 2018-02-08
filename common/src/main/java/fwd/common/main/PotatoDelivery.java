package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoDelivery {

    private int quantity;

    public PotatoDelivery() {
        // ;
    }

    public PotatoDelivery(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }
}
