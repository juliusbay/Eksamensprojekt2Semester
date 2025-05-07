package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Choice;
import org.example.eksamensprojekt2semester.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ChoiceRepository {

    @Autowired
    DataSource dataSource;

    public Choice getChoiceById(int id){
        Choice choice = new Choice();
        String sql = "SELECT * FROM choice WHERE choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    choice.setChoiceId(resultSet.getInt("choice_id"));
                    choice.setChoiceName(resultSet.getString("choice_name"));
                    choice.setChoicePrice(resultSet.getDouble("choice_price"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return choice;
    }

    // Method for creating employee users. ID is auto-increment in DB, so that is not used.
    public void createChoice(Choice choice){
        String sql = "INSERT INTO choice (choice_name, choice_price) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, choice.getChoiceName());
            statement.setDouble(2, choice.getChoicePrice());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateChoice(Choice choice){
        String sql = "UPDATE choice SET choice_name = ?, choice_price = ? " +
                "WHERE choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, choice.getChoiceName());
            statement.setDouble(2, choice.getChoicePrice());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteChoiceById(int id){
        String sql = "DELETE FROM choice WHERE choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
