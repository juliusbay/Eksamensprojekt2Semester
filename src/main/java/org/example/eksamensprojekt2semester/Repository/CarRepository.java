package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository

public class CarRepository {

   @Autowired
   DataSource dataSource;

    public Car getCarById(int id) {
        Car car = new Car();
        String sql = "SELECT * FROM car WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    car.setVehicleId(resultSet.getInt("vehicle_id"));
                    car.setFkCarModelId(resultSet.getInt("fk_car_model_id"));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setMonthlyPrice(resultSet.getDouble("monthly_price"));
                    car.setBought(resultSet.getBoolean("bought"));
                    car.setStatusFromString(resultSet.getString("status"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Car car = new Car();

                car.setVehicleId(resultSet.getInt("vehicle_id"));
                car.setFkCarModelId(resultSet.getInt("fk_car_model_id"));
                car.setVinNumber(resultSet.getString("vin_number"));
                car.setColor(resultSet.getString("color"));
                car.setMonthlyPrice(resultSet.getDouble("monthly_price"));
                car.setBought(resultSet.getBoolean("bought"));
                car.setStatusFromString(resultSet.getString("status"));

                cars.add(car);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cars;

    }


    public void createCar(Car car){
        String sql = "INSERT INTO car(fk_car_model_id, vin_number, color, monthly_price, bought, status) VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getFkCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setDouble(4, car.getMonthlyPrice());
            statement.setBoolean(5, car.isBought());
            statement.setString(6, car.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateCar(Car car) {
        String sql = "UPDATE car SET vehicle_id = ?, fk_car_model_id = ?, vin_number = ?, color = ?, monthly_price = ?, bought = ?, status = ? " +
                "WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getFkCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setDouble(4, car.getMonthlyPrice());
            statement.setBoolean(5, car.isBought());
            statement.setString(6, car.getStatus().name()); //Stores the string value of the Status enum by converting it to a string using .name();
                                                                // OBS!!! Hvis der er problemer med setStatus, så er det muligvis her det sker.
                                                                //Dog "burde" den automatisk konvertere tilbage til enum i databasen.
            statement.setInt(7,car.getVehicleId());

            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public void saveCar(Car car) {
        String sql = "INSERT INTO car(fk_car_model_id, vin_number, color, monthly_price, bought, status) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getFkCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setDouble(4, car.getMonthlyPrice());
            statement.setBoolean(5, car.isBought());
            statement.setString(6, car.getStatusValue());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
