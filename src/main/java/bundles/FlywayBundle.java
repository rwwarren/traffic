package bundles;

import bundles.configuration.FlywayBundleConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.flywaydb.core.Flyway;

public abstract class FlywayBundle<T extends Configuration> extends ConfiguredBundleV1<T, FlywayBundleConfiguration> {
//public abstract class FlywayBundle<T extends FlywayBundleConfiguration> {


    private static final Logger logger = LoggerFactory.getLogger(FlywayBundle.class);

    public static <T extends Configuration> FlywayBundle<T> create(String moduleName, Function<T, FlywayBundleConfiguration> configFunction) {
        Objects.requireNonNull(configFunction, "configFunction cannot be null!");
        return new FlywayBundle<T>(moduleName) {
            @Override
            protected FlywayBundleConfiguration extractBundleConfiguration(T t) throws Exception {
                return configFunction.apply(t);
            }
        };
    }

    private final String moduleName;


    public FlywayBundle(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    protected void runBundle(FlywayBundleConfiguration config, Environment environment) throws Exception {
        applyChanges(config, environment, moduleName);
    }

    public static void applyChanges(FlywayBundleConfiguration config, Environment environment, String moduleName) throws Exception {
        if (config.getDisableFlyway()) {
            logger.warn("Flyway migration skipped due to config.disableFlyway=true");
            return;
        }

        ManagedDataSource db = config.getDataSourceFactory().build(environment.metrics(), moduleName);

        Flyway flyway = new Flyway();
        flyway.setDataSource(db);

        setString(config.getTable(), flyway::setTable);
        setString(config.getBaselineVersion(), flyway::setBaselineVersionAsString);
        setString(config.getBaselineDescription(), flyway::setBaselineDescription);
        setValue(config.getBaselineOnMigrate(), flyway::setBaselineOnMigrate);
        setValue(config.getOutOfOrder(), flyway::setOutOfOrder);
//        setValue(config.getIgnoreMissingMigrations(), flyway::setIgnoreMissingMigrations);
        setString(config.getTarget(), flyway::setTargetAsString);
        setValue(config.getPlaceholderReplacement(), flyway::setPlaceholderReplacement);
        setValue(config.getPlaceholders(), flyway::setPlaceholders);
        setString(config.getPlaceholderPrefix(), flyway::setPlaceholderPrefix);
        setString(config.getPlaceholderSuffix(), flyway::setPlaceholderSuffix);
        setString(config.getSqlMigrationPrefix(), flyway::setSqlMigrationPrefix);
        setString(config.getRepeatableSqlMigrationPrefix(), flyway::setRepeatableSqlMigrationPrefix);
        setString(config.getSqlMigrationSeparator(), flyway::setSqlMigrationSeparator);
        setString(config.getSqlMigrationSuffix(), flyway::setSqlMigrationSuffix);
        setValue(config.getCleanDisabled(), flyway::setCleanDisabled);
        setValue(config.getCleanOnValidationError(), flyway::setCleanOnValidationError);
        setValue(config.getIgnoreFutureMigrations(), flyway::setIgnoreFutureMigrations);
        setValue(config.getValidateOnMigrate(), flyway::setValidateOnMigrate);
        setArray(config.getLocations(), flyway::setLocations);
        setArray(config.getSchemas(), flyway::setSchemas);

        flyway.migrate();
        db.stop();
    }

    private static void setString(String value, Consumer<String> setter) {
        if ((value != null) && !value.isEmpty())
            setter.accept(value);
    }

    private static <T> void setValue(T value, Consumer<T> setter) {
        if (value != null)
            setter.accept(value);
    }

    private static void setArray(List<String> values, Consumer<String[]> setter) {
        if ((values != null) && !values.isEmpty())
            setter.accept(values.toArray(new String[values.size()]));
    }




//    private static final Logger logger = LoggerFactory.getLogger(FlywayBundle.class);
//
//    private final String moduleName;

//    @Override
//    public void initialize(Bootstrap<FlywayBundleConfiguration> bootstrap) {
//        // ...
//        bootstrap.addBundle(new FlywayBundle<FlywayBundleConfiguration>() {
//            @Override
//            public DataSourceFactory getDataSourceFactory(FlywayBundleConfiguration configuration) {
//                return configuration.getDataSourceFactory();
//            }
//
//            @Override
//            public FlywayFactory getFlywayFactory(FlywayBundleConfiguration configuration) {
//                return configuration.getFlywayFactory();
//            }
//        });
//    }


//    public FlywayBundle(String moduleName) {
//        this.moduleName = moduleName;
//    }

//    @Override
//    protected void runBundle(FlywayBundleConfiguration config, Environment environment) throws Exception {
//        applyChanges(config, environment, moduleName);
//    }

//    public static void applyChanges(FlywayBundleConfiguration config, Environment environment, String moduleName) throws Exception {
//        if (config.getDisableFlyway()) {
//            logger.warn("Flyway migration skipped due to config.disableFlyway=true");
//            return;
//        }

//        ManagedDataSource db = config.getDataSourceFactory().build(environment.metrics(), moduleName);
//
//
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(db);
//
//        setString(config.getTable(), flyway::setTable);
//        setString(config.getBaselineVersion(), flyway::setBaselineVersionAsString);
//        setString(config.getBaselineDescription(), flyway::setBaselineDescription);
//        setValue(config.getBaselineOnMigrate(), flyway::setBaselineOnMigrate);
//        setValue(config.getOutOfOrder(), flyway::setOutOfOrder);
//        setValue(config.getIgnoreMissingMigrations(), flyway::setIgnoreMissingMigrations);
//        setString(config.getTarget(), flyway::setTargetAsString);
//        setValue(config.getPlaceholderReplacement(), flyway::setPlaceholderReplacement);
//        setValue(config.getPlaceholders(), flyway::setPlaceholders);
//        setString(config.getPlaceholderPrefix(), flyway::setPlaceholderPrefix);
//        setString(config.getPlaceholderSuffix(), flyway::setPlaceholderSuffix);
//        setString(config.getSqlMigrationPrefix(), flyway::setSqlMigrationPrefix);
//        setString(config.getRepeatableSqlMigrationPrefix(), flyway::setRepeatableSqlMigrationPrefix);
//        setString(config.getSqlMigrationSeparator(), flyway::setSqlMigrationSeparator);
//        setString(config.getSqlMigrationSuffix(), flyway::setSqlMigrationSuffix);
//        setValue(config.getCleanDisabled(), flyway::setCleanDisabled);
//        setValue(config.getCleanOnValidationError(), flyway::setCleanOnValidationError);
//        setValue(config.getIgnoreFutureMigrations(), flyway::setIgnoreFutureMigrations);
//        setValue(config.getValidateOnMigrate(), flyway::setValidateOnMigrate);
//        setArray(config.getLocations(), flyway::setLocations);
//        setArray(config.getSchemas(), flyway::setSchemas);
//
//        flyway.migrate();
//        db.stop();
//    }
//
//    private static void setString(String value, Consumer<String> setter) {
//        if ((value != null) && !value.isEmpty())
//            setter.accept(value);
//    }
//
//    private static <T> void setValue(T value, Consumer<T> setter) {
//        if (value != null)
//            setter.accept(value);
//    }
//
//    private static void setArray(List<String> values, Consumer<String[]> setter) {
//        if ((values != null) && !values.isEmpty())
//            setter.accept(values.toArray(new String[values.size()]));
//    }

}

