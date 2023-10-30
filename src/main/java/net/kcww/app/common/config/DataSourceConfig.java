package net.kcww.app.common.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = DataSourceConfig.REPOSITORY_PACKAGE_NAME,
  entityManagerFactoryRef = DataSourceConfig.ENTITY_MANAGER_FACTORY_NAME,
  transactionManagerRef = DataSourceConfig.TRANSACTION_MANAGER_NAME)
public class DataSourceConfig extends AbstractDataSourceConfig {

  public static final String REPOSITORY_PACKAGE_NAME = "net.kcww.app.common.repository";
  public static final String ENTITY_MANAGER_FACTORY_NAME = "webAppEntityManagerFactory";
  public static final String TRANSACTION_MANAGER_NAME = "webAppTransactionManager";

  private static final String DATASOURCE_PROPERTIES_NAME = "webAppDataSourceProperties";
  private static final String PROPERTIES_PREFIX = "spring.datasource";

  static final String DATA_SOURCE_NAME = "webAppDataSource";

  private static final String ENTITY_PACKAGE_NAME = "net.kcww.app.common.entity";
  private static final String PERSISTENCE_UNIT_NAME = "webapp";

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
  @Primary
  @Bean(name = DATA_SOURCE_NAME)
  public DataSource dataSource() {
    return createDataSource();
  }

  @Override
  @Primary
  @Bean(name = ENTITY_MANAGER_FACTORY_NAME)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env) {
    return createEntityManager(env);
  }

  @Override
  @Primary
  @Bean(name = TRANSACTION_MANAGER_NAME)
  public PlatformTransactionManager transactionManager(
    @Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory emf) {
    return createTransactionManager(emf);
  }

}