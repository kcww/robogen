package net.kcww.app.robogen.config;

import net.kcww.app.common.config.AbstractFlywayInitializer;
import net.kcww.app.hotel.config.HotelDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class RobogenFlywayInitializer extends AbstractFlywayInitializer {

  @Autowired
  public RobogenFlywayInitializer(@Value("${spring.datasource.robogen.flyway.locations}") String location,
                                  @Qualifier(RobogenDataSourceConfig.DATA_SOURCE_NAME) DataSource dataSource) {
    super(location, dataSource);
  }

}
