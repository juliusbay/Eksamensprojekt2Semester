package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Enum.FuelType;
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


    public CarModel getCarModelById (int id) {
        CarModel carModel = new CarModel();
        String sql = "SELECT * FROM car_model WHERE car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    carModel.setCarModelId(resultSet.getInt("car_model_id"));
                    carModel.setModelName(resultSet.getString("model_name"));
                    carModel.setBrand(resultSet.getString("brand"));
                    carModel.setFuelTypeFromString(resultSet.getString("fuel_type"));
                    carModel.setModelYear(resultSet.getInt("model_year"));
                    carModel.setGearBoxFromString(resultSet.getString("gear_box"));
                    carModel.setCarEmission(resultSet.getInt("car_emission"));
                    carModel.setCarEquipment(resultSet.getString("car_equipment"));
                    carModel.setSteelPrice(resultSet.getInt("steel_price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carModel;
    }

    public void createCarModel (CarModel carModel) {
        String sql = "INSERT INTO car_model (model_name, brand, fuel_type, model_year, gear_box, car_emission, car_equipment, steel_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getModelName());
            statement.setString(2, carModel.getBrand());
            statement.setString(3, carModel.getFuelType().name());
            statement.setInt(4, carModel.getModelYear());
            statement.setInt(5, carModel.getCarEmission());
            statement.setString(6, carModel.getCarEquipment());
            statement.setDouble(7, carModel.getSteelPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCarModel (CarModel carModel) {
        String sql = "UPDATE car_model SET model_name = ?, brand = ?, fuel_type = ?, model_year = ?, gear_box = ?, car_emission = ?, car_equipment = ?, steel_price = ?" +
                "WHERE car_model_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getModelName());
            statement.setString(2, carModel.getBrand());
            statement.setString(3, carModel.getFuelType().name());
            statement.setInt(4, carModel.getModelYear());
            statement.setInt(5, carModel.getCarEmission());
            statement.setString(6, carModel.getCarEquipment());
            statement.setDouble(7, carModel.getSteelPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarModelFromId (int id) {
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
