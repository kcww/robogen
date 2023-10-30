package net.kcww.app.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class FlywayInitializer extends AbstractFlywayInitializer {

  @Autowired
  public FlywayInitializer(@Value("${spring.datasource.flyway.locations}") String location,
                           @Qualifier(DataSourceConfig.DATA_SOURCE_NAME) DataSource dataSource) {
    super(location, dataSource);
  }

}