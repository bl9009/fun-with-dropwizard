package fwd.farmer.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class FarmerConfiguration extends Configuration
{
    private int productionRate;

    @JsonProperty
    public int getProductionRate()
    {
        return productionRate;
    }

    @JsonProperty
    public void setProductionRate(int productionRate)
    {
        this.productionRate = productionRate;
    }
}
