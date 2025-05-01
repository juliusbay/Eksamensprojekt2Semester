package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    @Autowired
    DataSource dataSource;

    // Method for retrieving specific users by userID
    public User getUserbyUserID(int id){
        User user = new User();
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        user.setUserID(resultSet.getInt("user_id"));
                        user.setFirstName(resultSet.getString("first_name"));
                        user.setLastName(resultSet.getString("last_name"));
                        user.setShortName(resultSet.getString("short_name"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPassword(resultSet.getString("password"));

                        // The string value from the DB needs to be converted to an Enum again before it can be set for the user.
                        String roleAsString = resultSet.getString("role");
                        User.Role role = User.Role.valueOf(roleAsString);
                        user.setRole(role);
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    // Method for creating users. ID is auto-increment in DB, so that is not used.
    public void createUser(User user){
        String sql = "INSERT INTO users (first_name, last_name, short_name, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getShortName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());

            // The Role enum from the user object needs to be converted to a string before it can be stored in the database.
            String roleAsString = user.getRole().toString();
            statement.setString(6, roleAsString);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method for updating users in the database.
    public void updateUser(User user){
        String sql = "UPDATE users SET (first_name = ?, last_name = ?, short_name = ?, email = ?, password = ?, role = ?) WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getShortName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());

            // The Role enum from the user object needs to be converted to a string before it can be stored in the database.
            String roleAsString = user.getRole().toString();
            statement.setString(6, roleAsString);

            statement.setInt(7, user.getUserID());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method for deleting users by their userID.
    public void deleteUserByUserID(int id){
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
