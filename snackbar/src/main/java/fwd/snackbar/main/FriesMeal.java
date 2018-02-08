package fwd.snackbar.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FriesMeal {

    private MealSize size;
    private Topping topping;

    public FriesMeal(MealSize size, Topping topping) {
        this.size = size;
        this.topping = topping;
    }

    @JsonProperty
    MealSize getSize() {
        return size;
    }

     @JsonProperty
    Topping getTopping() {
        return topping;
     }
}
