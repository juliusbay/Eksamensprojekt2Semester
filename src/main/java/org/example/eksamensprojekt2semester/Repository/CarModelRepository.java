package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CarModelRepository {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public CarModel getCarModelById (int id) {
        CarModel carModel = new CarModel();
        String sql = "SELECT * FROM car_model WHERE car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    carModel.setCarModelId(resultSet.getInt("car_model_id"));
                    carModel.setModelYear(resultSet.getInt("model_year"));
                    carModel.setBrand(resultSet.getString("brand"));
                    carModel.setModel(resultSet.getString("model"));
                    carModel.setCarEmission(resultSet.getInt("car_emission"));

                    String carEquipmentAsString = resultSet.getString("car_equipment");
                    CarModel.CarEquipment carEquipment = CarModel.CarEquipment.valueOf(carEquipmentAsString);
                    carModel.setCarEquipment(carEquipment);

                    carModel.setSteelPrice(resultSet.getInt("steel_price"));
                    carModel.setRegistrationFee(resultSet.getInt("registration_fee"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carModel;
    }

    public void createCarModel (CarModel carModel) {
        String sql = "INSERT INTO car_model (model_year, brand, model, car_emission, car_equipment, steel_price, registration_fee) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carModel.getModelYear());
            statement.setString(2, carModel.getBrand());
            statement.setString(3, carModel.getModel());
            statement.setInt(4, carModel.getCarEmission());
            statement.setString(5, carModel.getCarEquipment().toString()); // enum to string
            statement.setDouble(6, carModel.getSteelPrice());
            statement.setInt(7, carModel.getRegistrationFee());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCarModel (CarModel carModel) {
        String sql = "UPDATE car_model SET model_year = ?, brand = ?, model = ?, car_emission = ?, car_equipment = ?, steel_price = ?, registration_fee = ?" +
                "WHERE car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, carModel.getModelYear());
            statement.setString(2, carModel.getBrand());
            statement.setString(3, carModel.getModel());
            statement.setInt(4, carModel.getCarEmission());
            statement.setString(5, carModel.getCarEquipment().toString()); // enum to string
            statement.setDouble(6, carModel.getSteelPrice());
            statement.setInt(7, carModel.getRegistrationFee());
            statement.setInt(8, carModel.getCarModelId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarModelById (int id) {
        String sql = "DELETE FROM car_model WHERE car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
