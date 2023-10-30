package net.kcww.app.common.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractDataSourceConfig {

  private static Map<String, Object> jpaPropertiesCache;

  public static synchronized Map<String, Object> getJpaProperties(Environment env) {
    if (jpaPropertiesCache == null) {
      jpaPropertiesCache = List.of(
          "spring.jpa.hibernate.ddl-auto",
          "spring.jpa.defer-datasource-initialization",
          "hibernate.implicit_naming_strategy",
          "hibernate.physical_naming_strategy")
        .stream()
        .filter(key -> env.getProperty(key) != null)
        .collect(Collectors.toMap(key -> key, env::getProperty));
    }
    return jpaPropertiesCache;
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