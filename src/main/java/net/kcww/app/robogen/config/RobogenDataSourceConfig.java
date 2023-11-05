package net.kcww.app.robogen.config;

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
  basePackages = RobogenDataSourceConfig.REPOSITORY_PACKAGE_NAME,
  entityManagerFactoryRef = RobogenDataSourceConfig.ENTITY_MANAGER_FACTORY_NAME,
  transactionManagerRef = RobogenDataSourceConfig.TRANSACTION_MANAGER_NAME)
public class RobogenDataSourceConfig extends AbstractDataSourceConfig {

  public static final String REPOSITORY_PACKAGE_NAME = "net.kcww.app.robogen.repository";
  public static final String ENTITY_MANAGER_FACTORY_NAME = "robogenEntityManagerFactory";
  public static final String TRANSACTION_MANAGER_NAME = "robogenTransactionManager";

  private static final String DATASOURCE_PROPERTIES_NAME = "robogenDataSourceProperties";
  private static final String PROPERTIES_PREFIX = "spring.datasource.robogen";

  static final String DATA_SOURCE_NAME = "robogenDataSource";

  private static final String ENTITY_PACKAGE_NAME = "net.kcww.app.robogen.entity";
  private static final String PERSISTENCE_UNIT_NAME = "robogen";

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