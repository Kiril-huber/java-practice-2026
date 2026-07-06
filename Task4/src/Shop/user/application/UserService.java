package Shop.user.application;

import Shop.user.domain.User;
import Shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, String profileDescription){
        User user = new User(email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public Optional<User> findById(String userId){
        return userRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void updateUserData(User updateUser,String data){
        userRepository.updateUserData(updateUser,data);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
