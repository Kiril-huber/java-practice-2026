package ru.itis.Shop.app;

import ru.itis.Shop.Util.PropertiesReader;
import ru.itis.Shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import ru.itis.Shop.user.api.UserConsoleOperations;
import ru.itis.Shop.user.application.UserService;
import ru.itis.Shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader("application.properties");
        Properties properties = propertiesReader.loadProperties();

        DataSource dataSource = new DriverManagerDataSource(properties.getProperty("db.url"),properties.getProperty("db.user"), properties.getProperty("db.password"));
        UserService userService = new UserService(new UserRepositoryJdbcImpl(dataSource));
        UserConsoleOperations userConsoleOperations = new UserConsoleOperations(userService);

        while (true) {
            userConsoleOperations.showMenu();
        }
    }
}
