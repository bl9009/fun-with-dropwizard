package fwd.factory.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriesDelivery {

    private int quantity;

    public FriesDelivery(int quantity) {
        this.quantity = quantity;
    }

    public FriesDelivery() {
        // ;
    }

    @JsonProperty
    public int getQuantity() {
        return quantity;
    }

    @JsonProperty
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
