package net.kcww.app.common.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class AbstractDataSourceConfig {

    private static final String[] JPA_PROPERTY_NAMES = {
            "spring.jpa.hibernate.ddl-auto",
            "spring.jpa.defer-datasource-initialization",
            "hibernate.implicit_naming_strategy",
            "hibernate.physical_naming_strategy"
    };

    private static final ConcurrentHashMap<String, Object> jpaPropertiesCache = new ConcurrentHashMap<>();

    public static Map<String, Object> getJpaProperties(Environment env) {
        if (jpaPropertiesCache.isEmpty()) {
            var newCache = initializeJpaPropertiesCache(env);
            jpaPropertiesCache.putAll(newCache);
        }
        return jpaPropertiesCache;
    }

    private static Map<String, Object> initializeJpaPropertiesCache(Environment env) {
        return Arrays.stream(JPA_PROPERTY_NAMES)
                .map(key -> Optional.ofNullable(env.getProperty(key))
                        .map(value -> new AbstractMap.SimpleEntry<>(key, value)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    protected abstract String getEntityPackageName();

    protected abstract String getPersistenceUnitName();

    protected abstract DataSourceProperties dataSourceProperties();

    protected abstract DataSource dataSource();

    protected abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env);

    protected abstract PlatformTransactionManager transactionManager(EntityManagerFactory emf);

    protected DataSourceProperties createDataSourceProperties() {
        return new DataSourceProperties();
    }

    protected DataSource createDataSource() {
        return dataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    protected LocalContainerEntityManagerFactoryBean createEntityManager(Environment env) {
        var jpaVendorAdapter = new HibernateJpaVendorAdapter();
        var builder = new EntityManagerFactoryBuilder(jpaVendorAdapter, getJpaProperties(env), null);
        return builder
                .dataSource(dataSource())
                .packages(getEntityPackageName())
                .persistenceUnit(getPersistenceUnitName())
                .build();
    }

    protected PlatformTransactionManager createTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
