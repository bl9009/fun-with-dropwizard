package fwd.factory.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriesOrder {

    private int quantity;

    public FriesOrder(int quantity) {
        this.quantity = quantity;
    }

    public FriesOrder() {
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
