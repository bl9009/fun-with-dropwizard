package fwd.common.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus {
    RECEIVED("received"),
    PROCESSING("processing"),
    COMPLETED("completed");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

/*    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }*/
}
