package Shop.user.repository;

import Shop.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void updateUserData(User user,String profileDescription);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);
}
