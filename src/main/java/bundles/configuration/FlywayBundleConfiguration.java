package bundles.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FlywayBundleConfiguration {

    @JsonProperty
    private boolean disableFlyway = false;

    @JsonProperty
    private Boolean ignoreFutureMigrations;

    @JsonProperty
    private Boolean validateOnMigrate;

    @JsonProperty
    private Boolean cleanOnValidationError;

    @JsonProperty
    private Boolean cleanDisabled;

    @JsonProperty
    private List<String> locations;

    @JsonProperty
    private List<String> schemas;

    @JsonProperty
    private String table;

    @JsonProperty
    private String target;

    @JsonProperty
    private Boolean placeholderReplacement;

    @JsonProperty
    private Map<String, String> placeholders;

    @JsonProperty
    private String placeholderPrefix;

    @JsonProperty
    private String placeholderSuffix;

    @JsonProperty
    private String sqlMigrationPrefix;

    @JsonProperty
    private String repeatableSqlMigrationPrefix;

    @JsonProperty
    private String sqlMigrationSeparator;

    @JsonProperty
    private String sqlMigrationSuffix;

    @NotNull
    @Valid
    @JsonProperty
    private DataSourceFactory dataSourceFactory;

    @JsonProperty
    private String baselineVersion;

    @JsonProperty
    private String baselineDescription;

    @JsonProperty
    private Boolean baselineOnMigrate;

    @JsonProperty
    private Boolean outOfOrder;

    @JsonProperty
    private Boolean ignoreMissingMigrations;

    public FlywayBundleConfiguration() {
    }

    public boolean getDisableFlyway() {
        return disableFlyway;
    }

    public void setDisableFlyway(boolean value) {
        this.disableFlyway = value;
    }

    public Boolean getIgnoreFutureMigrations() {
        return ignoreFutureMigrations;
    }

    public FlywayBundleConfiguration setIgnoreFutureMigrations(Boolean ignoreFutureMigrations) {
        this.ignoreFutureMigrations = ignoreFutureMigrations;
        return this;
    }

    public Boolean getValidateOnMigrate() {
        return validateOnMigrate;
    }

    public FlywayBundleConfiguration setValidateOnMigrate(Boolean validateOnMigrate) {
        this.validateOnMigrate = validateOnMigrate;
        return this;
    }

    public Boolean getCleanOnValidationError() {
        return cleanOnValidationError;
    }

    public FlywayBundleConfiguration setCleanOnValidationError(Boolean cleanOnValidationError) {
        this.cleanOnValidationError = cleanOnValidationError;
        return this;
    }

    public Boolean getCleanDisabled() {
        return cleanDisabled;
    }

    public FlywayBundleConfiguration setCleanDisabled(Boolean cleanDisabled) {
        this.cleanDisabled = cleanDisabled;
        return this;
    }

    public List<String> getLocations() {
        return locations;
    }

    public FlywayBundleConfiguration setLocations(List<String> locations) {
        this.locations = locations;
        return this;
    }

    public List<String> getSchemas() {
        return schemas;
    }

    public FlywayBundleConfiguration setSchemas(List<String> schemas) {
        this.schemas = schemas;
        return this;
    }

    public String getTable() {
        return table;
    }

    public FlywayBundleConfiguration setTable(String table) {
        this.table = table;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public FlywayBundleConfiguration setTarget(String target) {
        this.target = target;
        return this;
    }

    public Boolean getPlaceholderReplacement() {
        return placeholderReplacement;
    }

    public FlywayBundleConfiguration setPlaceholderReplacement(Boolean placeholderReplacement) {
        this.placeholderReplacement = placeholderReplacement;
        return this;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public FlywayBundleConfiguration setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
        return this;
    }

    public String getPlaceholderPrefix() {
        return placeholderPrefix;
    }

    public FlywayBundleConfiguration setPlaceholderPrefix(String placeholderPrefix) {
        this.placeholderPrefix = placeholderPrefix;
        return this;
    }

    public String getPlaceholderSuffix() {
        return placeholderSuffix;
    }

    public FlywayBundleConfiguration setPlaceholderSuffix(String placeholderSuffix) {
        this.placeholderSuffix = placeholderSuffix;
        return this;
    }

    public String getSqlMigrationPrefix() {
        return sqlMigrationPrefix;
    }

    public FlywayBundleConfiguration setSqlMigrationPrefix(String sqlMigrationPrefix) {
        this.sqlMigrationPrefix = sqlMigrationPrefix;
        return this;
    }

    public String getRepeatableSqlMigrationPrefix() {
        return repeatableSqlMigrationPrefix;
    }

    public FlywayBundleConfiguration setRepeatableSqlMigrationPrefix(String repeatableSqlMigrationPrefix) {
        this.repeatableSqlMigrationPrefix = repeatableSqlMigrationPrefix;
        return this;
    }

    public String getSqlMigrationSeparator() {
        return sqlMigrationSeparator;
    }

    public FlywayBundleConfiguration setSqlMigrationSeparator(String sqlMigrationSeparator) {
        this.sqlMigrationSeparator = sqlMigrationSeparator;
        return this;
    }

    public String getSqlMigrationSuffix() {
        return sqlMigrationSuffix;
    }

    public FlywayBundleConfiguration setSqlMigrationSuffix(String sqlMigrationSuffix) {
        this.sqlMigrationSuffix = sqlMigrationSuffix;
        return this;
    }

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public FlywayBundleConfiguration setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
        return this;
    }

    public String getBaselineVersion() {
        return baselineVersion;
    }

    public FlywayBundleConfiguration setBaselineVersion(String baselineVersion) {
        this.baselineVersion = baselineVersion;
        return this;
    }

    public String getBaselineDescription() {
        return baselineDescription;
    }

    public FlywayBundleConfiguration setBaselineDescription(String baselineDescription) {
        this.baselineDescription = baselineDescription;
        return this;
    }

    public Boolean getBaselineOnMigrate() {
        return baselineOnMigrate;
    }

    public FlywayBundleConfiguration setBaselineOnMigrate(Boolean baselineOnMigrate) {
        this.baselineOnMigrate = baselineOnMigrate;
        return this;
    }

    public Boolean getOutOfOrder() {
        return outOfOrder;
    }

    public FlywayBundleConfiguration setOutOfOrder(Boolean outOfOrder) {
        this.outOfOrder = outOfOrder;
        return this;
    }

    public Boolean getIgnoreMissingMigrations() {
        return ignoreMissingMigrations;
    }

    public FlywayBundleConfiguration setIgnoreMissingMigrations(Boolean ignoreMissingMigrations) {
        this.ignoreMissingMigrations = ignoreMissingMigrations;
        return this;
    }


    @Override
    public String toString() {
        return "FlywayBundleConfiguration{" +
                "disableFlyway=" + disableFlyway +
                ", ignoreFutureMigrations=" + ignoreFutureMigrations +
                ", validateOnMigrate=" + validateOnMigrate +
                ", cleanOnValidationError=" + cleanOnValidationError +
                ", cleanDisabled=" + cleanDisabled +
                ", locations=" + locations +
                ", schemas=" + schemas +
                ", table='" + table + '\'' +
                ", target='" + target + '\'' +
                ", placeholderReplacement=" + placeholderReplacement +
                ", placeholders=" + placeholders +
                ", placeholderPrefix='" + placeholderPrefix + '\'' +
                ", placeholderSuffix='" + placeholderSuffix + '\'' +
                ", sqlMigrationPrefix='" + sqlMigrationPrefix + '\'' +
                ", repeatableSqlMigrationPrefix='" + repeatableSqlMigrationPrefix + '\'' +
                ", sqlMigrationSeparator='" + sqlMigrationSeparator + '\'' +
                ", sqlMigrationSuffix='" + sqlMigrationSuffix + '\'' +
                ", dataSourceFactory=" + dataSourceFactory +
                ", baselineVersion='" + baselineVersion + '\'' +
                ", baselineDescription='" + baselineDescription + '\'' +
                ", baselineOnMigrate=" + baselineOnMigrate +
                ", outOfOrder=" + outOfOrder +
                ", ignoreMissingMigrations=" + ignoreMissingMigrations +
                '}';
    }


}
