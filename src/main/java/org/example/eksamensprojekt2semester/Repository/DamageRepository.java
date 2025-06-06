package org.example.eksamensprojekt2semester.Repository;


import org.example.eksamensprojekt2semester.Model.Damage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Lavet af Frederik og Julius
@Repository
public class DamageRepository {


    @Autowired
    DataSource dataSource;

    public ArrayList<Damage> getAllDamages() {
        ArrayList<Damage> damages = new ArrayList<>();
        String sql = "SELECT * FROM damage";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Damage damage = new Damage();
                damage.setDamageId(resultSet.getInt("damage_id"));
                damage.setConditionReportId(resultSet.getInt("fk_condition_report_id"));
                damage.setDamageType(resultSet.getString("damage_type"));
                damages.add(damage);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return damages;
    }



        public Damage getDamageByID(int id) {
        Damage damage = new Damage();
        String sql = "SELECT * FROM damage WHERE damage_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    damage.setDamageId(resultSet.getInt("damage_id"));
                    damage.setConditionReportId(resultSet.getInt("fk_condition_report_id"));
                    damage.setDamageType(resultSet.getString("damage_type"));
                    damage.setDamagePrice(resultSet.getDouble("damage_price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        String sql = "INSERT INTO damage (fk_condition_report_id, damage_price, damage_type) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damage.getConditionReportId());
            statement.setDouble(2, damage.getDamagePrice());
            statement.setString(3, damage.getDamageType());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDamage(Damage damage) {
        String sql = "UPDATE damage SET fk_condition_report_id = ?, damage_price = ?, damage_type = ? WHERE damage_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setInt(1, damage.getConditionReportId());
            statement.setDouble(2, damage.getDamagePrice());
            statement.setString(3, damage.getDamageType());
            statement.setInt(4, damage.getDamageId());

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Damage> getDamageByConditionReportId(int conditionReportId) {
        ArrayList<Damage> damages = new ArrayList<>();
        String sql = "SELECT * FROM damage WHERE fk_condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conditionReportId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Damage damage = new Damage();
                    damage.setDamageId(resultSet.getInt("damage_id"));
                    damage.setConditionReportId(resultSet.getInt("fk_condition_report_id"));
                    damage.setDamageType(resultSet.getString("damage_type"));
                    damage.setDamagePrice(resultSet.getDouble("damage_price"));
                    damages.add(damage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return damages;
    }

}
