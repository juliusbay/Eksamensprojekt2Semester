package org.example.eksamensprojekt2semester.Repository;


import org.example.eksamensprojekt2semester.Model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Lavet og Tobias og Frederik
@Repository
public class CarModelRepository {

    @Autowired
    DataSource dataSource;


    public ArrayList<CarModel> getAllCarModels() {
        ArrayList<CarModel> carModels = new ArrayList<>();
        String sql = "SELECT * FROM car_model";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                CarModel carModel = new CarModel();
                carModel.setCarModelId(resultSet.getInt("car_model_id"));
                carModel.setModelName(resultSet.getString("model_name"));
                carModel.setBrand(resultSet.getString("brand"));
                carModel.setFuelTypeFromString(resultSet.getString("fuel_type"));
                carModel.setModelYear(resultSet.getInt("model_year"));
                carModel.setGearBoxFromString(resultSet.getString("gear_box"));
                carModel.setCarEmission(resultSet.getInt("car_emission"));
                carModel.setCarEquipment(resultSet.getString("car_equipment"));
                carModel.setSteelPrice(resultSet.getDouble("steel_price"));

                carModels.add(carModel);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carModels;
    }

    // lavet af Tobias
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

    // lavet af Tobias
    public void createCarModel (CarModel carModel) {
        String sql = "INSERT INTO car_model (model_name, brand, fuel_type, model_year, gear_box, car_emission, car_equipment, steel_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, carModel.getModelName());
            statement.setString(2, carModel.getBrand());
            statement.setString(3, carModel.getFuelType().name());
            statement.setInt(4, carModel.getModelYear());
            statement.setString(5, carModel.getGearBox().name());
            statement.setInt(6, carModel.getCarEmission());
            statement.setString(7, carModel.getCarEquipment());
            statement.setDouble(8, carModel.getSteelPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
