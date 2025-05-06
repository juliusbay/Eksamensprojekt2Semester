package org.example.eksamensprojekt2semester.Repository;


import org.example.eksamensprojekt2semester.Model.Damage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class DamageRepository {


    @Autowired
    DataSource dataSource;

    public Damage getDamageByID(int id) {
        Damage damage = new Damage();
        String sql = "SELECT * FROM damage WHERE damage_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    damage.setDamageId(resultSet.getInt("damage_id"));
                    damage.setVehicleId(resultSet.getInt("vehicle_id"));
                    damage.setDamageType(resultSet.getString("damage_type"));
                    damage.setDamageDate(resultSet.getDate("damage_date"));
                    damage.setDamagePrice(resultSet.getDouble("damage_price"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return damage;
    }

    public void deleteDamageByID(int id) {
        String sql = "DELETE FROM damage WHERE damage_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDamage(Damage damage) {
        String sql = "INSERT INTO damage (damage_id, vehicle_id, damage_date, damage_price, damage_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damage.getDamageId());
            statement.setInt(2, damage.getVehicleId());
            statement.setDate(3, damage.getDamageDate());
            statement.setDouble(4, damage.getDamagePrice());
            statement.setString(5, damage.getDamageType());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDamage(Damage damage) throws SQLException {
        String sql = "UPDATE damage SET damage_id = ?, vehicle_id = ?, damage_date = ?, damage_price = ?, damage_type = ? WHERE damage_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setInt(1, damage.getDamageId());
            statement.setInt(2, damage.getVehicleId());
            statement.setDate(3, damage.getDamageDate());
            statement.setDouble(4, damage.getDamagePrice());
            statement.setString(5, damage.getDamageType());

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
