package fwd.factory.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class FactoryConfiguration extends Configuration {

    private int productionRate;

    private int friesPerPotato;

    public FactoryConfiguration() {
        // ;
    }

    public FactoryConfiguration(int productionRate, int friesPerPotato) {
        this.productionRate = productionRate;
        this.friesPerPotato = friesPerPotato;
    }

    @JsonProperty
    public int getProductionRate() {
        return productionRate;
    }

    @JsonProperty
    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    @JsonProperty
    public int getFriesPerPotato() {
        return friesPerPotato;
    }

    @JsonProperty
    public void setFriesPerPotato(int friesPerPotato) {
        this.friesPerPotato = friesPerPotato;
    }
}
