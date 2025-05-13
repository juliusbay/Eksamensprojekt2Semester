package org.example.eksamensprojekt2semester.Repository;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class LeaseAgreementRepository {


    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomerRepository customerRepository;


    public ArrayList<LeaseAgreement> getAllLeaseAgreements() {
        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        String sql = "SELECT * FROM lease_agreement";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LeaseAgreement leaseAgreement = new LeaseAgreement();
                leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                leaseAgreement.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                leaseAgreement.setLeaseType(LeaseAgreement.LeaseType.valueOf(resultSet.getString("lease_type")));
                leaseAgreement.setLeasePrice(resultSet.getInt("lease_price"));
                leaseAgreement.setLeaseStartDate(resultSet.getDate("lease_start_date"));
                leaseAgreement.setLeaseEndDate(resultSet.getDate("lease_end_date"));
                leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                leaseAgreement.setActive(resultSet.getBoolean("lease_active"));

                leaseAgreements.add(leaseAgreement);
            }
        }catch (SQLException e){
                e.printStackTrace();
        }
        return leaseAgreements;

    }



    public LeaseAgreement getLeaseAgreementById(int id) throws SQLException {
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        String sql = "SELECT * FROM lease_agreement WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                    leaseAgreement.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                    leaseAgreement.setLeaseType(LeaseAgreement.LeaseType.valueOf(resultSet.getString("lease_type")));
                    leaseAgreement.setLeasePrice(resultSet.getInt("lease_price"));
                    leaseAgreement.setLeaseStartDate(resultSet.getDate("lease_start_date"));
                    leaseAgreement.setLeaseEndDate(resultSet.getDate("lease_end_date"));
                    leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                    leaseAgreement.setActive(resultSet.getBoolean("lease_active"));

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
        String sql = "INSERT INTO lease_agreement (fk_vehicle_id, fk_customer_id, lease_type, lease_price, lease_start_date, lease_end_date, return_location) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, leaseAgreement.getFkVehicleId());
                statement.setInt(2, leaseAgreement.getCustomer().getCustomerId());
                statement.setString(3, leaseAgreement.getLeaseType().toString());
                statement.setDouble(4, leaseAgreement.getLeasePrice());
                statement.setDate(5, leaseAgreement.getLeaseStartDate());
                statement.setDate(6, leaseAgreement.getLeaseEndDate());
                statement.setString(7, leaseAgreement.getReturnLocation());

                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLeaseAgreement(LeaseAgreement leaseAgreement) throws SQLException {
        String sql = "UPDATE lease_agreement SET fk_vehicle_id = ?, fk_customer_id = ?, lease_type = ?, lease_price = ?, lease_start_date = ?, lease_end_date = ?, return_location = ?, lease_active = ? WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, leaseAgreement.getFkVehicleId());
                statement.setInt(2, leaseAgreement.getCustomer().getCustomerId());
                statement.setString(3, leaseAgreement.getLeaseType().toString());
                statement.setDouble(4, leaseAgreement.getLeasePrice());
                statement.setDate(5, leaseAgreement.getLeaseStartDate());
                statement.setDate(6, leaseAgreement.getLeaseEndDate());
                statement.setString(7, leaseAgreement.getReturnLocation());
                statement.setBoolean(8, leaseAgreement.isActive());

                statement.setInt(9, leaseAgreement.getLeaseAgreementId());


                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
        }

    }

    public void setLeaseAgreementActive(LeaseAgreement leaseAgreement) throws SQLException {
        String sql = "UPDATE lease_agreement SET lease_active = ? WHERE lease_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1, leaseAgreement.isActive());
            statement.setInt(2, leaseAgreement.getLeaseAgreementId());

            statement.executeUpdate();


        }
    }


}





