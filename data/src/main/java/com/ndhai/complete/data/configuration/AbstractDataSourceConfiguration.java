package com.ndhai.complete.data.configuration;

import org.apache.tomcat.jdbc.pool.DataSourceProxy;
import org.apache.tomcat.jdbc.pool.jmx.ConnectionPool;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbstractDataSourceConfiguration {
    protected  ConnectionPool getJmxPool(DataSource dataSource) throws SQLException {
        return ((DataSourceProxy) dataSource).createPool().getJmxPool();
    }

    public abstract DataSource dataSource();

    public abstract ConnectionPool jmxPool(DataSource dataSource) throws SQLException;

    protected PlatformTransactionManager getTransactionManager(DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    public abstract PlatformTransactionManager transactionManager(DataSource dataSource);

    protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(this.getPackageScan());
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName(this.getPersistentUnitName());
        em.setJpaPropertyMap(this.getJpaPropertyMap());
        return em;
    }

    public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource);

    protected abstract String[] getPackageScan();

    protected abstract String getPersistentUnitName();

    protected abstract Map<String, String> getJpaPropertyMap();
}
