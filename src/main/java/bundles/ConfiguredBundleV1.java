package bundles;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.Objects;

public abstract class ConfiguredBundleV1<ServiceConfig extends Configuration, BundleConfig> implements io.dropwizard.ConfiguredBundle<ServiceConfig> {

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }

    @Override
    public void run(ServiceConfig serviceConfig, Environment environment) throws Exception {
        BundleConfig bundleConfig = Objects.requireNonNull(extractBundleConfiguration(serviceConfig), "bundleConfig cannot be null!");
        runBundle(bundleConfig, environment);
    }

    protected abstract BundleConfig extractBundleConfiguration(ServiceConfig serviceConfig) throws Exception;

    protected abstract void runBundle(BundleConfig bundleConfig, Environment environment) throws Exception;
}

