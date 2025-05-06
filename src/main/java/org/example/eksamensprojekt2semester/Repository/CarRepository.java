package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository

public class CarRepository {

   @Autowired
   DataSource dataSource;

    public Car getCarById(int id) {
        Car car = new Car();
        String sql = "SELECT * FROM rental_car WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    car.setCarModelId(resultSet.getInt("car_model_id"));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setReturnAddress(resultSet.getString("return_address"));
                    car.setMonthlyPrice(resultSet.getDouble("price"));
                    car.setMileage(resultSet.getInt("mileage"));

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }


    public void createCar(Car car){
        String sql = "INSERT INTO rental_car(car_model_id, vin_number, color, return_address, monthly_price, mileage) VALUES(?,?,?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setString(4, car.getReturnAddress());
            statement.setDouble(5, car.getMonthlyPrice());
            statement.setDouble(6, car.getMileage());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateCar(Car car) {
        String sql = "UPDATE rental_car SET vehicle_id = ?, car_model_id = ?, vin_number = ?, color = ?, return_address = ?, monthly_price = ?, mileage = ?, status = ? WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,car.getVehicleId());
            statement.setInt(2,car.getCarModelId());
            statement.setString(3,car.getVinNumber());
            statement.setString(4,car.getColor());
            statement.setString(5,car.getReturnAddress());
            statement.setDouble(6,car.getMonthlyPrice());
            statement.setInt(7,car.getMileage());

            statement.setString(8,car.getStatus().name()); //Stores the string value of the Status enum by converting it to a string using .name();
                                                                // OBS!!! Hvis der er problemer med setStatus, så er det muligvis her det sker. Dog "burde" den automatisk konvertere tilbage til enum i databasen.
            statement.setInt(9,car.getVehicleId());

            statement.executeUpdate();



        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
