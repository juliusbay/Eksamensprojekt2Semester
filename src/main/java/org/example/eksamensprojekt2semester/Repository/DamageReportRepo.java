package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DamageReportRepo {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public DamageReport getDamageReportById (int id) {
        DamageReport damageReport = new DamageReport();
        String sql = "SELECT * FROM damage_reports WHERE report_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    damageReport.setReportId(resultSet.getInt("report_id"));
                    damageReport.setVehicleId(resultSet.getInt("vehicle_id"));
                    damageReport.setReportDate(resultSet.getDate("report_date"));
                    damageReport.setDamageType(resultSet.getString("damage_type"));
                    damageReport.setDamagePrice(resultSet.getDouble("damage_price"));
                    damageReport.setHandledBy(resultSet.getInt("handled_by"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damageReport;
    }

    public ArrayList<DamageReport> getDamageReportByUserId (int userId) {
        ArrayList<DamageReport> listOfDamageReports = new ArrayList<>();
        String sql = "SELECT dr.report_id, dr.vehicle_id, dr.report_date, dr.damage_type, dr.damage_price, users.user_id " +
                "FROM damage_reports dr " +
                "INNER JOIN users ON dr.handled_by = users.user_id " +
                "WHERE handled_by = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DamageReport damageReport = new DamageReport(
                            resultSet.getInt("report_id"),
                            resultSet.getInt("vehicle_id"),
                            resultSet.getDate("report_date"),
                            resultSet.getString("damage_type"),
                            resultSet.getDouble("damage_price"),
                            resultSet.getInt("user_id")
                    );
                    listOfDamageReports.add(damageReport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfDamageReports;
    }

    public void createDamageReport (DamageReport damageReport) {
        String sql ="INSERT INTO damage_reports (vehicle_id, report_date, damage_type, damage_price, handled_by) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getVehicleId());
            statement.setDate(2, damageReport.getReportDate());
            statement.setString(3, damageReport.getDamageType());
            statement.setDouble(4, damageReport.getDamagePrice());
            statement.setInt(5, damageReport.getHandledBy());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDamageReport (DamageReport damageReport) {
        String sql = "UPDATE damage_reports SET vehicle_id = ?, report_date = ?, damage_type = ?, damage_price = ?, handled_by = ?" +
                "WHERE report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, damageReport.getVehicleId());
            statement.setDate(2, damageReport.getReportDate());
            statement.setString(3, damageReport.getDamageType());
            statement.setDouble(4, damageReport.getDamagePrice());
            statement.setInt(5, damageReport.getHandledBy());
            statement.setInt(6, damageReport.getReportId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
