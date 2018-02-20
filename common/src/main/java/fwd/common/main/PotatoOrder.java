package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoOrder {

    private String customer;
    private int quantity;

    private OrderStatus status;

    public PotatoOrder() {
        status = OrderStatus.RECEIVED;
    }

    public PotatoOrder(String customer, int quantity, OrderStatus status) {
        this.customer = customer;
        this.quantity = quantity;

        this.status = status;
    }

    public PotatoOrder(String customer, int quantity) {
        this(customer, quantity, OrderStatus.RECEIVED);
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
    public OrderStatus getStatus() {
        return this.status;
    }

    @JsonProperty
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
