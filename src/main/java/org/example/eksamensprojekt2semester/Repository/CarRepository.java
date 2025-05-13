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
import java.util.List;

@Repository

public class CarRepository {

   @Autowired
   DataSource dataSource;

   @Autowired
   CarModelRepository carModelRepo;

    public Car getCarById(int id) {
        Car car = new Car();
        String sql = "SELECT * FROM car WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    car.setVehicleId(resultSet.getInt("vehicle_id"));
                    car.setCarModel(carModelRepo.getCarModelById(resultSet.getInt("car_model_id")));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setBought(resultSet.getBoolean("bought"));
                    car.setStatusFromString(resultSet.getString("status"));
                    car.setRentedOut(resultSet.getBoolean("rented_out"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public Car getCarByVinNumber(String vinNumber) {
        Car car = new Car();
        String sql = "SELECT * FROM car WHERE vin_number = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,vinNumber);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    car.setVehicleId(resultSet.getInt("vehicle_id"));
                    car.setCarModel(carModelRepo.getCarModelById(resultSet.getInt("car_model_id")));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setBought(resultSet.getBoolean("bought"));
                    car.setStatusFromString(resultSet.getString("status"));
                    car.setRentedOut(resultSet.getBoolean("rented_out"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT car.vehicle_id, car.vin_number, cm.car_model_id, cm.brand, cm.model_name, cm.car_equipment, car.color, car.bought, car.status, car.rented_out " +
                "FROM car " +
                "INNER JOIN car_model cm ON car.fk_car_model_id = cm.car_model_id";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Car car = new Car();

                car.setVehicleId(resultSet.getInt("vehicle_id"));
                car.setCarModel(carModelRepo.getCarModelById(resultSet.getInt("car_model_id")));
                car.setVinNumber(resultSet.getString("vin_number"));
                car.setColor(resultSet.getString("color"));
                car.setBought(resultSet.getBoolean("bought"));
                car.setStatusFromString(resultSet.getString("status"));
                car.setRentedOut(resultSet.getBoolean("rented_out"));

                cars.add(car);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public int getCarsByRentedOutStatus(boolean isRentedOut){
        int amountOfCarsRentedOut = 0;
        String sql = "SELECT COUNT(*) FROM car WHERE rented_out = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setBoolean(1, isRentedOut);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    amountOfCarsRentedOut = resultSet.getInt(1);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return amountOfCarsRentedOut;
    }

    public boolean existsByVinNumber(String vinNumber) {
        String sql = "SELECT 1 FROM car WHERE vin_number = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,vinNumber);
            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public void createCar(Car car){
        String sql = "INSERT INTO car(fk_car_model_id, vin_number, color, bought, status) " +
                "VALUES(?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getCarModel().getCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setBoolean(4, car.isBought());
            statement.setString(5, car.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarById(int id){
        String sql = "DELETE FROM car WHERE vehicle_id = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        String sql = "UPDATE car SET vehicle_id = ?, fk_car_model_id = ?, vin_number = ?, color = ?, bought = ?, status = ?, rented_out = ? " +
                "WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,car.getVehicleId());
            statement.setInt(2, car.getCarModel().getCarModelId());
            statement.setString(3, car.getVinNumber());
            statement.setString(4, car.getColor());
            statement.setBoolean(5, car.isBought());
            statement.setString(6, car.getStatus().name()); //Stores the string value of the Status enum by converting it to a string using .name();
            statement.setBoolean(7, car.isBought());        // OBS!!! Hvis der er problemer med setStatus, så er det muligvis her det sker.
                                                                //Dog "burde" den automatisk konvertere tilbage til enum i databasen.
            statement.setInt(8,car.getVehicleId());
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
