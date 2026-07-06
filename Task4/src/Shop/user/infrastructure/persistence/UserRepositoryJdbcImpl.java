package Shop.user.infrastructure.persistence;

import Shop.user.domain.User;
import Shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl  implements UserRepository {

    @Override
    public void save(User user) {}

    @Override
    public void updateUserData(User user, String profileDescription) {}

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Kiril20070912")) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("select * from account")) {

                    while (resultSet.next()) {
                        userList.add(new User(resultSet.getString("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getInt("age")));
                    }
                }
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }

        return userList;
    }
}
