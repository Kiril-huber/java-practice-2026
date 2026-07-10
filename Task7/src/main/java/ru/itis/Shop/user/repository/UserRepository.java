package ru.itis.Shop.user.repository;

import ru.itis.Shop.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void updateUserData(String email, String profileDescription);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    List<User> findAll();

    List<User> findAllByProfileDescription(String profileDescription);
}
