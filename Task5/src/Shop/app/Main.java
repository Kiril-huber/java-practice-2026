package Shop.app;

import Shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import Shop.user.api.UserConsoleOperations;
import Shop.user.application.UserService;
import Shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/postgres","postgres","Kiril20070912");
        UserService userService = new UserService(new UserRepositoryJdbcImpl(dataSource));
        UserConsoleOperations userConsoleOperations = new UserConsoleOperations(userService);

        while (true) {
            userConsoleOperations.showMenu();
        }
    }
}
