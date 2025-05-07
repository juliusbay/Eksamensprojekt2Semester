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
        String sql = "SELECT * FROM cars WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    car.setCarModelId(resultSet.getInt("fk_car_model_id"));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setMonthlyPrice(resultSet.getDouble("price"));

                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Car car = new Car();
                car.setVehicleId(resultSet.getInt("vehicle_id"));
                car.setCarModelId(resultSet.getInt("fk_car_model_id"));
                car.setVinNumber(resultSet.getString("vin_number"));
                car.setColor(resultSet.getString("color"));
                car.setMonthlyPrice(resultSet.getDouble("monthly_price"));

                cars.add(car);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cars;

    }


    public void createCar(Car car){
        String sql = "INSERT INTO cars(car_model_id, vin_number, color, monthly_price) VALUES(?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setDouble(4, car.getMonthlyPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateCar(Car car) {
        String sql = "UPDATE cars SET vehicle_id = ?, fk_car_model_id = ?, vin_number = ?, color = ?, monthly_price = ?, status = ? WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,car.getVehicleId());
            statement.setInt(2,car.getCarModelId());
            statement.setString(3,car.getVinNumber());
            statement.setString(4,car.getColor());
            statement.setDouble(5,car.getMonthlyPrice());

            statement.setString(6,car.getStatus().name()); //Stores the string value of the Status enum by converting it to a string using .name();
                                                                // OBS!!! Hvis der er problemer med setStatus, så er det muligvis her det sker. Dog "burde" den automatisk konvertere tilbage til enum i databasen.
            statement.setInt(7,car.getVehicleId());

            statement.executeUpdate();



        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public void saveCar(Car car) {
        String sql = "INSERT INTO cars(fk_car_model_id, vin_number,color, monthly_price) VALUES(?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,car.getCarModelId());
            statement.setString(2,car.getVinNumber());
            statement.setString(3,car.getColor());
            statement.setDouble(4,car.getMonthlyPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
