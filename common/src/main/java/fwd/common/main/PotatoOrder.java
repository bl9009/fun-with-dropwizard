package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoOrder {

    private String customer;
    private int quantity;

    private OrderState state;

    public PotatoOrder() {
        // ;
    }

    public PotatoOrder(String customer, int quantity, OrderState state) {
            this.customer = customer;
            this.quantity = quantity;

            this.state = state;
    }

    public PotatoOrder(String customer, int quantity) {
        this(customer, quantity, OrderState.RECEIVED);
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

    @JsonProperty
    public OrderState getState() {
        return this.state;
    }

    @JsonProperty
    public void setState(OrderState state) {
        this.state = state;
    }

}
