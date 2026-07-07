package Shop.user.infrastructure.persistence.jdbc;

import Shop.infrastructure.persistence.jdbc.RowMapper;
import Shop.user.domain.User;
import Shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl  implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = (row ->
            new User(row.getInt("id"),row.getString("first_name"),
                    row.getString("email"), row.getString("password"),
                    row.getString("profile_description"))
            );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {}

    @Override
    public void updateUserData(User user, String profileDescription) {}

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("select * from account")) {

                    while (resultSet.next()) {
                        userList.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }

        return userList;
    }


    @Override
    public List<User> findAllByProfileDescription(String profileDescription) {
        List<User> userList = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){

            try (Statement statement = connection.createStatement()){

                try(ResultSet resultSet = statement.executeQuery("select * from account where profile_description = '" + profileDescription + "'")){
                    while(resultSet.next()) {
                        userList.add(userRowMapper.mapRow(resultSet));
                    }
                }


            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
        return userList;
    }
}
