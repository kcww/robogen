package net.kcww.app.hotel.config;

import net.kcww.app.common.config.AbstractFlywayInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class HotelFlywayInitializer extends AbstractFlywayInitializer {

  @Autowired
  public HotelFlywayInitializer(@Value("${spring.datasource.hotel.flyway.locations}") String location,
                                @Qualifier(HotelDataSourceConfig.DATA_SOURCE_NAME) DataSource dataSource) {
    super(location, dataSource);
  }

}
