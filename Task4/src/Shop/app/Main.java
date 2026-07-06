package Shop.app;

import Shop.user.api.UserConsoleOperations;
import Shop.user.application.UserService;
import Shop.user.infrastructure.persistence.UserFileRepository;
import Shop.user.infrastructure.persistence.UserMapper;
import Shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService(new UserRepositoryJdbcImpl());
        UserConsoleOperations userConsoleOperations = new UserConsoleOperations(userService);

        while (true) {
            userConsoleOperations.showMenu();
        }
    }
}
