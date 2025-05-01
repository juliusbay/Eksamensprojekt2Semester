package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DamageReportRepo {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public DamageReport getDmgReportById (int id) {
        DamageReport dmgReport = new DamageReport();
        String sql = "SELECT * FROM damage_reports WHERE report_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    dmgReport.setReportId(resultSet.getInt("report_id"));
                    dmgReport.setVehicleId(resultSet.getInt("vehicle_id"));
                    dmgReport.setReportDate(resultSet.getDate("report_date"));
                    dmgReport.setDamageType(resultSet.getString("damage_type"));
                    dmgReport.setDamagePrice(resultSet.getDouble("damage_price"));
                    dmgReport.setHandledBy(userRepository.getUserbyUserId(resultSet.getInt("handled_by")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dmgReport;
    }
}
