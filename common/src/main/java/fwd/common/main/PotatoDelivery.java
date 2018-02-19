package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PotatoDelivery {

    private String recipient;
    private int quantity;

    public PotatoDelivery() {
        // ;
    }

    public PotatoDelivery(String recipient, int quantity) {
        this.recipient = recipient;
        this.quantity = quantity;
    }

    @JsonProperty
    public String getRecipient() {
        return recipient;
    }

    @JsonProperty
    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
