package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoOrder {

    private String customer;
    private int quantity;

    public PotatoOrder() {
        // ;
    }

    public PotatoOrder(String customer, int quantity) {
        this.customer = customer;
        this.quantity = quantity;
    }

    @JsonProperty
    public String getCustomer() {
        return customer;
    }

    @JsonProperty
    public void setCustomer(String customer) {
        this.customer = customer;
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
