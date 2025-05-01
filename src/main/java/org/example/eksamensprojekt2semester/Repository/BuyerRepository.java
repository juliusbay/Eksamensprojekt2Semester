package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BuyerRepository {

    @Autowired
    DataSource dataSource;

    //Method for getting buyer by their BuyerID
    public Buyer getBuyerbyBuyerId(int id){
        Buyer buyer = new Buyer();
        String sql = "SELECT * FROM buyers WHERE buyer_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        buyer.setFullName(resultSet.getString("full_name"));
                        buyer.setEmail(resultSet.getString("email"));
                        buyer.setPhoneNumber(resultSet.getInt("phone_number"));
                        buyer.setPreBought(resultSet.getBoolean("is_pre_bought"));
                        buyer.setVehicleID(resultSet.getInt("vehicle_id"));
                    }
                }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return buyer;
    }

    //Method for creating buyer.
    public void createBuyer(Buyer buyer){
        String sql = "INSERT INTO buyers (full_name, email, phone_number, is_pre_bought, vehicle_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, buyer.getFullName());
            statement.setString(2, buyer.getEmail());
            statement.setInt(3, buyer.getPhoneNumber());
            statement.setBoolean(4, buyer.isPreBought());
            statement.setInt(5, buyer.getVehicleID());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateBuyer(Buyer buyer){
        String sql = "UPDATE buyers (full_name = ?, email = ?, phone_number = ?, is_pre_bought = ?, vehicle_id = ?) WHERE buyer_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, buyer.getFullName());
            statement.setString(2, buyer.getEmail());
            statement.setInt(3, buyer.getPhoneNumber());
            statement.setBoolean(4, buyer.isPreBought());
            statement.setInt(5, buyer.getVehicleID());

            statement.setInt(6,buyer.getBuyerId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
