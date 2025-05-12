package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.ConditionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ConditionReportRepository {

    @Autowired
    DataSource dataSource;

    public Map<Integer, ConditionReport> getAllConditionReports() {
        Map<Integer, ConditionReport> conditionReports = new HashMap<>();
        String sql = "SELECT * FROM condition_report";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ConditionReport conditionReport = new ConditionReport();
                conditionReport.setConditionReportId(resultSet.getInt("condition_report_id"));
                conditionReport.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                conditionReport.setHandledBy(resultSet.getString("handled_by"));
                conditionReport.setReportDate(resultSet.getDate("report_date"));
                conditionReports.put(conditionReport.getFkVehicleId(), conditionReport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conditionReports;
    }
        public ConditionReport getConditionReportByVehicleId (int vehicleId) {
        ConditionReport conditionReport = new ConditionReport();
        String sql = "SELECT * FROM condition_report WHERE fk_vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    conditionReport.setConditionReportId(resultSet.getInt("condition_report_id"));
                    conditionReport.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    conditionReport.setHandledBy(resultSet.getString("handled_by"));
                    conditionReport.setReportDate(resultSet.getDate("report_date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conditionReport;
    }

    public ArrayList<ConditionReport> getConditionReportByEmployeeId (int employeeId) {
        ArrayList<ConditionReport> listOfConditionReports = new ArrayList<>();
        String sql = "SELECT conRepo.condition_report_id, conRepo.fk_vehicle_id, employee.short_name, conRepo.report_date " +
                "FROM condition_report conRepo " +
                "INNER JOIN employee ON conRepo.handled_by = employee.short_name " +
                "WHERE handled_by = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, employeeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ConditionReport conditionReport = new ConditionReport(
                            resultSet.getInt("condition_report_id"),
                            resultSet.getInt("fk_vehicle_id"),
                            resultSet.getString("handled_by"),
                            resultSet.getDate("report_date")
                    );
                    listOfConditionReports.add(conditionReport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfConditionReports;
    }

    public void createConditionReport (ConditionReport conditionReport) {
        String sql = "INSERT INTO condition_report (fk_vehicle_id, handled_by, report_date) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conditionReport.getFkVehicleId());
            statement.setString(2, conditionReport.getHandledBy());
            statement.setDate(3, conditionReport.getReportDate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateConditionReport (ConditionReport conditionReport) {
        String sql = "UPDATE condition_report SET fk_vehicle_id = ?, handled_by = ?, report_date = ?" +
                "WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conditionReport.getFkVehicleId());
            statement.setString(2, conditionReport.getHandledBy());
            statement.setDate(3, conditionReport.getReportDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteConditionReportFromId (int id) {
        String sql = "DELETE FROM condition_report WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
