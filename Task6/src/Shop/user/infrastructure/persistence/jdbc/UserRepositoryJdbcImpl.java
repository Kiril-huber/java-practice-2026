package Shop.user.infrastructure.persistence.jdbc;

import Shop.infrastructure.persistence.jdbc.RowMapper;
import Shop.user.domain.User;
import Shop.user.repository.UserRepository;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

public class UserRepositoryJdbcImpl  implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = (row ->
            new User(row.getInt("id"),row.getString("first_name"),
                    row.getString("last_name"),row.getInt("age"),
                    row.getString("email"), row.getString("password"),
                    row.getString("profile_description"))
            );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try(Connection connection = dataSource.getConnection()){

            try(PreparedStatement preparedStatement = connection.prepareStatement("insert into account (first_name, last_name ,age, email, password, profile_description) values (?, ? ,? , ?, ?, ?)")){

                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setString(6, user.getProfileDescription());

                int affectedRowsCount = preparedStatement.executeUpdate();

                if(affectedRowsCount != 1){
                    throw new SQLException("Can't insert");
                }
            }
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateUserData(String email, String profileDescription) {
        try(Connection connection = dataSource.getConnection()){

            try(PreparedStatement preparedStatement = connection.prepareStatement("update account set profile_description = ? where email = ?")){

                preparedStatement.setString(1,profileDescription);
                preparedStatement.setString(2,email);

                int affectedRowsCount = preparedStatement.executeUpdate();

                if(affectedRowsCount != 1){
                    throw new SQLException("Can't update");
                }

            }
        }catch(SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try(Connection connection = dataSource.getConnection()){

            try(PreparedStatement preparedStatement= connection.prepareStatement("select * from account where email = ?")) {

                preparedStatement.setString(1,email);

                try (ResultSet resultSet = preparedStatement.executeQuery()){

                    if(resultSet.next()){
                        return Optional.of(userRowMapper.mapRow(resultSet));
                    }
                }

            }

            return Optional.empty();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        try(Connection connection = dataSource.getConnection()){

            try(PreparedStatement preparedStatement = connection.prepareStatement("select * from account where id = ?")){

                preparedStatement.setInt(1,id);

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        return Optional.of(userRowMapper.mapRow(resultSet));
                    }
                }

            }
            return Optional.empty();
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }
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

            try (PreparedStatement preparedStatement = connection.prepareStatement("select * from account where profile_description = ?")){

                preparedStatement.setString(1,profileDescription);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        userList.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }

            return userList;
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }

    }
}
