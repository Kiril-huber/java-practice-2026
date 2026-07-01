package Shop.app;

import Shop.user.api.UserConsoleOperations;
import Shop.user.application.UserService;
import Shop.user.infrastructure.persistence.UserFileRepository;
import Shop.user.infrastructure.persistence.UserMapper;

public class Main {
    public static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("users.txt",new UserMapper());
        UserService userService = new UserService(userFileRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
