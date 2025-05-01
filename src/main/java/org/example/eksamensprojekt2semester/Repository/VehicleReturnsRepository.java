package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.RentalCar;
import org.example.eksamensprojekt2semester.Model.VehicleReturns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class VehicleReturnsRepository {
    @Autowired
    DataSource dataSource;
    public VehicleReturns getVehicleReturn(int id){
        VehicleReturns vehicleReturn = new VehicleReturns();
        String sql = "SELECT * FROM vehicle_returns WHERE return_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    vehicleReturn.setReturn_id(resultSet.getInt("return_id"));
                    vehicleReturn.setVehicle_id(resultSet.getInt("vehicle_id"));
                    vehicleReturn.setReturn_date(resultSet.getDate("return_date"));
                    vehicleReturn.setReturnedTo(VehicleReturns.ReturnedTo.valueOf(resultSet.getString("returned_to"))); // OBS!!! Ikke helt sikker på om det virker som det skal. Den 'burde' konvertere til string, og automatisk konvertere tilbage i databasen.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleReturn;
    }
}
