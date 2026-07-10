package ru.itis.Shop.user.api;

import ru.itis.Shop.user.api.dto.UserDto;
import ru.itis.Shop.user.application.UserService;

import java.util.List;
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
            case "5":{
                findAll();
            }
            break;
            case "6":{
                findAllByProfileDescription();
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
        System.out.println("5. Показать всех пользователей");
        System.out.println("6. Показать информацию о пользователях с данным profileDescription");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите Имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите Фамилию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите Возраст:");
        Integer age = scanner.nextInt();
        System.out.println("Введите email:");
        scanner.nextLine();
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(firstName,lastName,age,email,password,profileDescription);

        System.out.println("Пользователь зарегестрирован!");
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
        Integer userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Поиск пользователя");
        Optional<UserDto> user = userService.findById(userId);
        if(user.isPresent()){
            System.out.println("Пользователь найден");
            System.out.println("name:" + user.get().getFirstName() + " описание профиля: " + user.get().getProfileDescription());
        }else{
            System.out.println("Пользователь с данным ID не найден :(");
        }
    }

    private void updateUserData() {
        System.out.println("Введите email пользователя");
        String email = scanner.nextLine();
        Optional<UserDto> user = userService.findByEmail(email);
        if(user.isPresent()){
            System.out.println("Пользователь найден!");
            System.out.println("Введите новые данные:");
            String profileDescription = scanner.nextLine();
            userService.updateUserData(email,profileDescription);
            System.out.println("Данные успешно обновлены!");
        }else{
            System.out.println("Пользователь не найден!");
        }
    }

    private void findAll(){
        System.out.println("Ищем всех пользователей...");
        System.out.println("Пользователи найдены:");
        List<UserDto> userList = userService.findAll();
        for(UserDto user : userList){
            System.out.println("name: " + user.getFirstName() + " email:" + user.getEmail() + " profileDescription: " + user.getProfileDescription());
        }
    }

    private void findAllByProfileDescription(){
        System.out.println("Введите profileDescription пользователя");
        String profileDescription = scanner.nextLine();
        System.out.println("Ищем пользователей...");
        List<UserDto> userList = userService.findAllByProfileDescription(profileDescription);
        if(userList.isEmpty()){
            System.out.println("Пользователи не найдны!");
            return;
        }
        for(UserDto user : userList){
            System.out.println(user);
        }
    }
}
