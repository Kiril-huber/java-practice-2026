package Shop.user.api;

import Shop.user.application.UserService;
import Shop.user.domain.User;

import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                singIn();
            }
            break;
            case "3": {
                findById();
            }
            break;
            case "4":{
                updateUserData();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(email,password,profileDescription);
    }

    private void singIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if(userService.signIn(email,password)){
            System.out.println("Вы вошли в приложение!");
        }else{
            System.out.println("Email или пароль введены не верно!");
        }
    }

    private void findById() {
        System.out.println("Введите ID пользователя");
        String userId = scanner.nextLine();
        System.out.println("Поиск пользователя");
        Optional<User> user = userService.findById(userId);
        if(user.isPresent()){
            System.out.println("Пользователь найден");
            System.out.println("email:" + user.get().getEmail());
        }else{
            System.out.println("Пользователь с данным ID не найден :(");
        }
    }

    private void updateUserData() {
        System.out.println("Введите email пользователя");
        String email = scanner.nextLine();
        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()){
            System.out.println("Пользователь найден!");
            System.out.println("Введите новые данные:");
            String profileDescription = scanner.nextLine();
            userService.updateUserData(user.get(),profileDescription);
            System.out.println("Данные успешно обновлены!");
        }else{
            System.out.println("Пользователь не найден!");
        }
    }
}
