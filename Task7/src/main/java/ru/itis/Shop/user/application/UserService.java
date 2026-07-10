package ru.itis.Shop.user.application;

import ru.itis.Shop.user.api.dto.UserDto;
import ru.itis.Shop.user.domain.User;
import ru.itis.Shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String firstName, String lastName, Integer age, String email, String password, String profileDescription){
        User user = new User(firstName,lastName,age,email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public Optional<UserDto> findById(Integer userId){
        return userRepository.findById(userId).map(user ->
                new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail(),user.getProfileDescription()));
    }

    public Optional<UserDto> findByEmail(String email){
        return userRepository.findByEmail(email).map(user ->
                new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail(),user.getProfileDescription()));
    }

    public void updateUserData(String email,String data){
        userRepository.updateUserData(email,data);
    }

    public List<UserDto> findAll(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail(),user.getProfileDescription())).toList();
    }

    public List<UserDto> findAllByProfileDescription(String profileDescription){
        List<User> userList = userRepository.findAllByProfileDescription(profileDescription);
        return userList.stream().map(user -> new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getAge(),user.getEmail(),user.getProfileDescription())).toList();
    }
}
