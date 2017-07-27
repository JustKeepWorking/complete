package com.ndhai.complete.data.configuration;

import org.apache.tomcat.jdbc.pool.jmx.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class SampleConfiguration extends  AbstractDataSourceConfiguration {
    @Autowired
    private JpaProperties jpaProperties;

    private final String DATA_SOURCE = "dataSource";
    private final String JMX_POOL = "jmxPool";
    private final String TRANSACTION_MANAGER = "sampleTransactionManager";
    private final String MANAGER_FACTORY = "sampleManagerFactory";
    private final String[] PACKAGE = {"complete.sample"};
    private final String UNIT_NAME = "sampleUnitName";

    @Override
    @ConfigurationProperties(prefix = "app.datasource.postgresql")
    @Bean(name = DATA_SOURCE)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Override
    @Bean(name = JMX_POOL)
    @ConditionalOnExpression("${app.datasource.sample.jmxEnabled:true}")
    public ConnectionPool jmxPool(@Qualifier(DATA_SOURCE) DataSource dataSource) throws SQLException {
        return this.getJmxPool(dataSource);
    }

    @Override
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        return this.getTransactionManager(dataSource);
    }

    @Override
    @Bean(name = MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(DATA_SOURCE) DataSource dataSource) {
        return this.getEntityManagerFactory(dataSource);
    }

    @Override
    protected String[] getPackageScan() {
        return PACKAGE;
    }

    @Override
    protected String getPersistentUnitName() {
        return UNIT_NAME;
    }

    @Override
    protected Map<String, String> getJpaPropertyMap() {
        return jpaProperties.getProperties();
    }
}
