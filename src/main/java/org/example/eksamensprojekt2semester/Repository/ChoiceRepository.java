package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.AvailableChoice;
import org.example.eksamensprojekt2semester.Model.Choice;
import org.example.eksamensprojekt2semester.Model.ChosenChoice;
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

    public Choice getChoiceById(int id) {
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

    // Method for creating an optional choice. ID is auto-increment in DB, so that is not used.
    public void createChoice(Choice choice) {
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

    public void updateChoice(Choice choice) {
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

    public void deleteChoiceById(int id) {
        String sql = "DELETE FROM choice WHERE choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // chosen choice
    public ChosenChoice getChosenChoiceByChoiceId(int id) {
        ChosenChoice chosenChoice = new ChosenChoice();
        String sql = "SELECT * FROM chosen_choice WHERE fk_choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    chosenChoice.setFkChoiceId(resultSet.getInt("fk_choice_id"));
                    chosenChoice.setFkLeaseAgreementId(resultSet.getInt("fk_lease_agreement_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chosenChoice;
    }

    public ChosenChoice getChosenChoiceByLeaseAgreementId(int id) {
        ChosenChoice chosenChoice = new ChosenChoice();
        String sql = "SELECT * FROM chosen_choice WHERE fk_lease_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    chosenChoice.setFkChoiceId(resultSet.getInt("fk_choice_id"));
                    chosenChoice.setFkLeaseAgreementId(resultSet.getInt("fk_lease_agreement_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chosenChoice;
    }

    public void createChosenChoice(ChosenChoice chosenChoice){
        String sql = "INSERT INTO chosen_choice (fk_choice_id, fk_lease_agreement_id) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, chosenChoice.getFkChoiceId());
            statement.setInt(2, chosenChoice.getFkLeaseAgreementId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateChosenChoice(ChosenChoice chosenChoice){
        String sql = "UPDATE chosen_choice SET fk_choice_id = ?, fk_lease_agreement_id = ? " +
                "WHERE (fk_choice_id = ?, fk_lease_agreement_id)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, chosenChoice.getFkChoiceId());
            statement.setInt(2, chosenChoice.getFkLeaseAgreementId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteChosenChoice(int choiceId, int leaseId){
        String sql = "DELETE FROM chosen_choice WHERE (fk_choice_id = ?, fk_lease_agreement_id = ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, choiceId);
            statement.setInt(2, leaseId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // available choice
    public AvailableChoice getAvailableChoiceByChoiceId(int id) {
        AvailableChoice availableChoice = new AvailableChoice();
        String sql = "SELECT * FROM chosen_choice WHERE fk_choice_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    availableChoice.setFkCarModelId(resultSet.getInt("fk_car_model_id"));
                    availableChoice.setFkChoiceId(resultSet.getInt("fk_choice_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return availableChoice;
    }

    public AvailableChoice getAvailableChoiceByCarModelId(int id) {
        AvailableChoice availableChoice = new AvailableChoice();
        String sql = "SELECT * FROM available_choice WHERE fk_car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    availableChoice.setFkCarModelId(resultSet.getInt("fk_car_model_id"));
                    availableChoice.setFkChoiceId(resultSet.getInt("fk_choice_id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return availableChoice;
    }

    public void createAvailableChoice(AvailableChoice availableChoice){
        String sql = "INSERT INTO available_choice (fk_car_model_id, fk_choice_id) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(2, availableChoice.getFkCarModelId());
            statement.setInt(1, availableChoice.getFkChoiceId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateAvailableChoice(AvailableChoice availableChoice){
        String sql = "UPDATE available_choice SET fk_car_model_id, fk_choice_id = ?" +
                "WHERE (fk_car_model_id, fk_choice_id = ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(2, availableChoice.getFkCarModelId());
            statement.setInt(1, availableChoice.getFkChoiceId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteAvailableChoice(int choiceId, int modelId){
        String sql = "DELETE FROM available_choice WHERE (fk_car_model_id, fk_choice_id = ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(2, modelId);
            statement.setInt(1, choiceId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
