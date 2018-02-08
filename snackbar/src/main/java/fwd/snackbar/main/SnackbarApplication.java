package fwd.snackbar.main;

import fwd.snackbar.resources.FriesMealResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class SnackbarApplication extends Application<SnackbarConfiguration> {

    public static void main(String[] args) throws Exception {
        new SnackbarApplication().run(args);
    }

    @Override
    public void run(SnackbarConfiguration snackbarConfiguration, Environment environment) {

        FriesMealResource resource = new FriesMealResource();

        environment.jersey().register(resource);
    }
}
