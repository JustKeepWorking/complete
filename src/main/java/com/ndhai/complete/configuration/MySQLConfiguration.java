package com.ndhai.complete.configuration;

import org.apache.tomcat.jdbc.pool.DataSourceProxy;
import org.apache.tomcat.jdbc.pool.jmx.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by nduyhai on 7/16/2017.
 */

@Configuration
public class MySQLConfiguration {

    @Bean
    @ConditionalOnExpression("${app.datasource.mysql.jmxEnabled:true}")
    public ConnectionPool getMySQLJmxPool(@Qualifier("MySQLDataSource") DataSource dataSource) throws SQLException {
        return ((DataSourceProxy)dataSource).createPool().getJmxPool();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.mysql")
    public DataSource MySQLDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager MySQLTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(MySQLDataSource());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(MySQLDataSource());
        entityManagerFactoryBean.setPersistenceUnitName("MySQLPersistentUnit");
        return entityManagerFactoryBean;
    }


}
