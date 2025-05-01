package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.RentalCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class RentalCarRepository {

   @Autowired
   DataSource dataSource;

    public RentalCar getRentalCarById(int id) {
        RentalCar rentalCar = new RentalCar();
        String sql = "SELECT * FROM rental_car WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rentalCar.setCar_model_id(resultSet.getInt("car_model_id"));
                    rentalCar.setVin_number(resultSet.getString("vin_number"));
                    rentalCar.setColor(resultSet.getString("color"));
                    rentalCar.setReturn_address(resultSet.getString("return_address"));
                    rentalCar.setMonthly_Price(resultSet.getDouble("price"));
                    rentalCar.setMileage(resultSet.getInt("mileage"));

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rentalCar;
    }


    public void createRentalCar(RentalCar rentalCar){
        String sql = "INSERT INTO rental_car(car_model_id, vin_number, color, return_address, monthly_price, mileage) VALUES(?,?,?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,rentalCar.getCar_model_id());
            statement.setString(2,rentalCar.getVin_number());
            statement.setString(3,rentalCar.getColor());
            statement.setString(4,rentalCar.getReturn_address());
            statement.setDouble(5,rentalCar.getMonthly_Price());
            statement.setDouble(6,rentalCar.getMileage());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateRentalCar(RentalCar rentalCar) {
        String sql = "UPDATE rental_car SET vehicle_id = ?, car_model_id = ?, vin_number = ?, color = ?, return_address = ?, monthly_price, mileage = ?, status = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,rentalCar.getVehicle_id());
            statement.setInt(2,rentalCar.getCar_model_id());
            statement.setString(3,rentalCar.getVin_number());
            statement.setString(4,rentalCar.getColor());
            statement.setString(5,rentalCar.getReturn_address());
            statement.setDouble(6,rentalCar.getMonthly_Price());
            statement.setInt(7,rentalCar.getMileage());

            statement.setString(8,rentalCar.getStatus().name()); //Stores the string value of the Status enum by converting it to a string using .name();
                                                                // OBS!!! Hvis der er problemer med setStatus, så er det muligvis her det sker. Dog "burde" den automatisk konvertere tilbage til enum i databasen.
            statement.setInt(9,rentalCar.getVehicle_id());

            statement.executeUpdate();



        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
