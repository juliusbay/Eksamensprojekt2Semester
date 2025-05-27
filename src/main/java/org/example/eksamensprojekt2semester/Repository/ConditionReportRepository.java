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
import java.util.List;
import java.util.Map;
//Lavet af Julius
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
                conditionReport.setReportStartDate(resultSet.getTimestamp("report_start_date"));
                conditionReport.setReportCompletedDate(resultSet.getTimestamp("report_completed_date"));
                conditionReport.setExcessKilometers(resultSet.getDouble("excess_kilometers"));
                conditionReport.setCompleted(resultSet.getBoolean("completed"));
                conditionReport.setReportDescription(resultSet.getString("report_description"));
                conditionReports.put(conditionReport.getFkVehicleId(), conditionReport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conditionReports;
    }

    // Returns list of completed condition reports, used for statistics.
    public List<ConditionReport> getConditionReportsByCompletionStatus (boolean completed) {
        List<ConditionReport> completedConditionReports = new ArrayList<>();
        ConditionReport conditionReport = new ConditionReport();
        String sql = "SELECT * FROM condition_report WHERE completed = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, completed);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    conditionReport.setConditionReportId(resultSet.getInt("condition_report_id"));
                    conditionReport.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    conditionReport.setHandledBy(resultSet.getString("handled_by"));
                    conditionReport.setReportStartDate(resultSet.getTimestamp("report_start_date"));
                    conditionReport.setReportCompletedDate(resultSet.getTimestamp("report_completed_date"));
                    conditionReport.setExcessKilometers(resultSet.getDouble("excess_kilometers"));
                    conditionReport.setCompleted(resultSet.getBoolean("completed"));
                    conditionReport.setReportDescription(resultSet.getString("report_description"));

                    completedConditionReports.add(conditionReport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completedConditionReports;
    }

        //Returns a condition report object based on the vehicle ID connected to the report.
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
                    conditionReport.setReportStartDate(resultSet.getTimestamp("report_start_date"));
                    conditionReport.setReportCompletedDate(resultSet.getTimestamp("report_completed_date"));
                    conditionReport.setExcessKilometers(resultSet.getDouble("excess_kilometers"));
                    conditionReport.setCompleted(resultSet.getBoolean("completed"));
                    conditionReport.setReportDescription(resultSet.getString("report_description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conditionReport;
    }

    // Fetches a condition report object based on the report ID
    public ConditionReport getConditionReportByReportId (int reportId) {
        ConditionReport conditionReport = new ConditionReport();
        String sql = "SELECT * FROM condition_report WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reportId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    conditionReport.setConditionReportId(resultSet.getInt("condition_report_id"));
                    conditionReport.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    conditionReport.setHandledBy(resultSet.getString("handled_by"));
                    conditionReport.setReportStartDate(resultSet.getTimestamp("report_start_date"));
                    conditionReport.setReportCompletedDate(resultSet.getTimestamp("report_completed_date"));
                    conditionReport.setExcessKilometers(resultSet.getDouble("excess_kilometers"));
                    conditionReport.setCompleted(resultSet.getBoolean("completed"));
                    conditionReport.setReportDescription(resultSet.getString("report_description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conditionReport;
    }

    // Fetches the vehicle ID based on the report ID
    public int getVehicleIdByConditionReportId (int conditionReportId) {
        int vehicleId = 0;
        String sql = "SELECT fk_vehicle_id FROM condition_report WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conditionReportId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    vehicleId = resultSet.getInt("fk_vehicle_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleId;
    }

    // Creates a new condition report in the database.
    public void createConditionReport (ConditionReport conditionReport) {
        String sql = "INSERT INTO condition_report (fk_vehicle_id, handled_by, report_start_date ) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, conditionReport.getFkVehicleId());
            statement.setString(2, conditionReport.getHandledBy());
            statement.setTimestamp(3, conditionReport.getReportStartDate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates only the description of the report and excess kilometers driven.
    public void updateExcessKilometersAndDescriptionFromConditionReportId (int reportId, double excessKilometers,
                                                                           String reportDescription) {

        String sql = "UPDATE condition_report SET excess_kilometers = ?, report_description = ? WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, excessKilometers);
            statement.setString(2, reportDescription);
            statement.setInt(3, reportId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates the entire condition reports completion info.
    public void updateConditionReport(ConditionReport conditionReport){
        String sql = "UPDATE condition_report SET handled_by = ?, report_completed_date = ?, completed = ? " +
                "WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, conditionReport.getHandledBy());
            statement.setTimestamp(2, conditionReport.getReportCompletedDate());
            statement.setBoolean(3, conditionReport.isCompleted());
            statement.setInt(4, conditionReport.getConditionReportId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletes all damages from the condition report before deleting the condition report - Julius
    public void deleteConditionReportFromId (int id) {
        String sql = "DELETE FROM damage WHERE fk_condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "DELETE FROM condition_report WHERE condition_report_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql2)){

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
