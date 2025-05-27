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
//Lavet af Noah og Julius
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
                    car.setCarModel(carModelRepo.getCarModelById(resultSet.getInt("fk_car_model_id")));
                    car.setVinNumber(resultSet.getString("vin_number"));
                    car.setColor(resultSet.getString("color"));
                    car.setBought(resultSet.getBoolean("bought"));
                    car.setStatusFromString(resultSet.getString("status"));
                    car.setReceivedDate(resultSet.getTimestamp("received_date"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return car;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT car.vehicle_id, car.vin_number, cm.car_model_id, cm.brand, cm.model_name, cm.car_equipment, car.color, car.bought, car.status, car.received_date " +
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
                car.setReceivedDate(resultSet.getTimestamp("received_date"));

                cars.add(car);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    // Returns the amount of cars with a specific status for statistics page.
    public int getAmountOfCarsByStatus(String status){
        int amountOfCars = 0;
        String sql = "SELECT COUNT(*) FROM car WHERE STATUS = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    amountOfCars = resultSet.getInt(1);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return amountOfCars;
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
        String sql = "INSERT INTO car(fk_car_model_id, vin_number, color, bought, status, received_date ) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, car.getCarModel().getCarModelId());
            statement.setString(2, car.getVinNumber());
            statement.setString(3, car.getColor());
            statement.setBoolean(4, car.isBought());
            statement.setString(5, car.getStatus().name());
            statement.setTimestamp(6, car.getReceivedDate());

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
        String sql = "UPDATE car SET vehicle_id = ?, fk_car_model_id = ?, vin_number = ?, color = ?, bought = ?, status = ? " +
                "WHERE vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,car.getVehicleId());
            statement.setInt(2, car.getCarModel().getCarModelId());
            statement.setString(3, car.getVinNumber());
            statement.setString(4, car.getColor());
            statement.setBoolean(5, car.isBought());
            statement.setString(6, car.getStatus().name());
            statement.setInt(7,car.getVehicleId());

            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
