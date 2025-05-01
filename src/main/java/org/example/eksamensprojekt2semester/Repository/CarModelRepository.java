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
}
