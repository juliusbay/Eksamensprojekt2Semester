package org.example.eksamensprojekt2semester.Repository;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LeaseAgreementRepository {


    @Autowired
    DataSource dataSource;

    public LeaseAgreement getLeaseAgreementById(int id) throws SQLException {
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        String sql = "SELECT * FROM lease_agreement WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                    leaseAgreement.setChosen_optionals(resultSet.getInt("optional_id"));
                    leaseAgreement.setVehicleId(resultSet.getInt("vehicle_id"));
                    leaseAgreement.setCustomerId(resultSet.getInt("customer_id"));
                    leaseAgreement.setLeaseType(LeaseAgreement.LeaseType.valueOf(resultSet.getString("lease_type")));
                    leaseAgreement.setLeasePrice(resultSet.getInt("lease_price"));
                    leaseAgreement.setLeaseStartDate(resultSet.getDate("lease_start_date"));
                    leaseAgreement.setLeaseEndDate(resultSet.getDate("lease_end_date"));
                    leaseAgreement.setInitialPayment(resultSet.getBoolean("initial_payment"));
                    leaseAgreement.setReturnLocation(resultSet.getString("return_location"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseAgreement;
    }

    public void deleteLeaseAgreementById(int id) throws SQLException {
        String sql = "DELETE FROM lease_agreement WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLeaseAgreement(LeaseAgreement leaseAgreement) throws SQLException {
        String sql = "INSERT INTO lease_agreement (chosen_optionals, vehicle_id, customer_id, lease_type, lease_price, lease_start_date, lease_end_date, initial_payment, return_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, leaseAgreement.getChosen_optionals());
                statement.setInt(2, leaseAgreement.getVehicleId());
                statement.setInt(3, leaseAgreement.getCustomerId());
                statement.setString(4, leaseAgreement.getLeaseType().toString());
                statement.setDouble(5, leaseAgreement.getLeasePrice());
                statement.setDate(6, leaseAgreement.getLeaseStartDate());
                statement.setDate(7, leaseAgreement.getLeaseEndDate());
                statement.setBoolean(8, leaseAgreement.isInitialPayment());
                statement.setString(9, leaseAgreement.getReturnLocation());

                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLeaseAgreement(LeaseAgreement leaseAgreement) throws SQLException {
        String sql = "UPDATE lease_agreement SET lease_agreement_id = ?, chosen_optionals = ?, vehicle_id = ?, customer_id = ?, lease_type = ?, lease_price = ?, lease_start_date = ?, lease_end_date = ?, initial_payment = ? WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, leaseAgreement.getLeaseAgreementId());
                statement.setInt(2, leaseAgreement.getChosen_optionals());
                statement.setInt(3, leaseAgreement.getVehicleId());
                statement.setInt(4, leaseAgreement.getCustomerId());
                statement.setString(5, leaseAgreement.getLeaseType().toString());
                statement.setDouble(6, leaseAgreement.getLeasePrice());
                statement.setDate(7, leaseAgreement.getLeaseStartDate());
                statement.setDate(8, leaseAgreement.getLeaseEndDate());
                statement.setBoolean(9, leaseAgreement.isInitialPayment());
                statement.setString(10, leaseAgreement.getReturnLocation());
                statement.setInt(11, leaseAgreement.getLeaseAgreementId());


                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
        }

    }
}





