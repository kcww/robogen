package net.kcww.app.common.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@AllArgsConstructor
public abstract class AbstractFlywayInitializer {

    private final String location;
    private final DataSource dataSource;

    @PostConstruct
    public void migrate() {
        Flyway.configure()
                .dataSource(dataSource)
                .locations(location)
                .load()
                .migrate();
    }
}
