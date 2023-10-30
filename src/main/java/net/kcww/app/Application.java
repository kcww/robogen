package net.kcww.app;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import lombok.AllArgsConstructor;
import net.kcww.app.hotel.helper.DatabaseSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@AllArgsConstructor
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class})
@Theme(value = "webapp")
public class Application implements AppShellConfigurator {

  private final DatabaseSeeder databaseSeeder;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doAfterStartup() {
    databaseSeeder.seedDatabase();
  }

}
