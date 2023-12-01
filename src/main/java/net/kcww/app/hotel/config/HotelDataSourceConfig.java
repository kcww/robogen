package net.kcww.app.hotel.config;

import jakarta.persistence.EntityManagerFactory;
import net.kcww.app.common.config.AbstractDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = HotelDataSourceConfig.REPOSITORY_PACKAGE_NAME,
        entityManagerFactoryRef = HotelDataSourceConfig.ENTITY_MANAGER_FACTORY_NAME,
        transactionManagerRef = HotelDataSourceConfig.TRANSACTION_MANAGER_NAME)
public class HotelDataSourceConfig extends AbstractDataSourceConfig {

    public static final String REPOSITORY_PACKAGE_NAME = "net.kcww.app.hotel.repository";
    public static final String ENTITY_MANAGER_FACTORY_NAME = "hotelEntityManagerFactory";
    public static final String TRANSACTION_MANAGER_NAME = "hotelTransactionManager";

    private static final String DATASOURCE_PROPERTIES_NAME = "hotelDataSourceProperties";
    private static final String PROPERTIES_PREFIX = "spring.datasource.hotel";

    static final String DATA_SOURCE_NAME = "hotelDataSource";

    private static final String ENTITY_PACKAGE_NAME = "net.kcww.app.hotel.entity";
    private static final String PERSISTENCE_UNIT_NAME = "hotel";


    @Override
    protected String getEntityPackageName() {
        return ENTITY_PACKAGE_NAME;
    }

    @Override
    protected String getPersistenceUnitName() {
        return PERSISTENCE_UNIT_NAME;
    }

    @Override
    @Bean(name = DATASOURCE_PROPERTIES_NAME)
    @ConfigurationProperties(prefix = PROPERTIES_PREFIX)
    public DataSourceProperties dataSourceProperties() {
        return createDataSourceProperties();
    }

    @Override
    @Bean(name = DATA_SOURCE_NAME)
    public DataSource dataSource() {
        return createDataSource();
    }

    @Override
    @Bean(name = ENTITY_MANAGER_FACTORY_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
        return createEntityManager(env);
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    public PlatformTransactionManager transactionManager(
            @Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory emf) {
        return createTransactionManager(emf);
    }

}