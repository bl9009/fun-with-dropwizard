package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoOrder {

    private int quantity;

    public PotatoOrder() {
        // ;
    }

    public PotatoOrder(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }

}
