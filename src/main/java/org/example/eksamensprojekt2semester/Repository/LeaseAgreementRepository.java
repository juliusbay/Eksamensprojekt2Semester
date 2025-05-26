package org.example.eksamensprojekt2semester.Repository;
import org.example.eksamensprojekt2semester.Enum.LeaseType;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class LeaseAgreementRepository {


    @Autowired
    DataSource dataSource;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;


    public ArrayList<LeaseAgreement> getAllLeaseAgreements() {
        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        String sql = "SELECT * FROM lease_agreement";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LeaseAgreement leaseAgreement = new LeaseAgreement();
                leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                leaseAgreement.setCar(carRepository.getCarById(resultSet.getInt("fk_vehicle_id")));
                leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                leaseAgreement.setLeaseTypeFromString((resultSet.getString("lease_type")));
                leaseAgreement.setLeasePrice(resultSet.getDouble("lease_price"));
                leaseAgreement.setLeaseStartDate(resultSet.getTimestamp("lease_start_date"));
                leaseAgreement.setLeaseEndDate(resultSet.getTimestamp("lease_end_date"));
                leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                leaseAgreement.setActive(resultSet.getBoolean("lease_active"));
                if (resultSet.getDate("lease_end_date").before(Date.valueOf(LocalDate.now()))) {
                    leaseAgreement.setActive(false);
                }

                leaseAgreements.add(leaseAgreement);
            }
        }catch (SQLException e){
                e.printStackTrace();
        }
        return leaseAgreements;

    }



    public LeaseAgreement getLeaseAgreementById(int id) {
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        String sql = "SELECT * FROM lease_agreement WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                    leaseAgreement.setCar(carRepository.getCarById(resultSet.getInt("fk_vehicle_id")));
                    leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                    leaseAgreement.setLeaseTypeFromString((resultSet.getString("lease_type")));
                    leaseAgreement.setLeasePrice(resultSet.getDouble("lease_price"));
                    leaseAgreement.setLeaseStartDate(resultSet.getTimestamp("lease_start_date"));
                    leaseAgreement.setLeaseEndDate(resultSet.getTimestamp("lease_end_date"));
                    leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                    leaseAgreement.setActive(resultSet.getBoolean("lease_active"));

                    if (resultSet.getDate("lease_end_date").before(Date.valueOf(LocalDate.now()))) {
                        leaseAgreement.setActive(false);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseAgreement;
    }

    // Returns the lease agreement from the vehicle ID connected to it.
    public LeaseAgreement getLeaseAgreementByVehicleId(int vehicleId)  {
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        String sql = "SELECT * FROM lease_agreement WHERE fk_vehicle_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vehicleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                    leaseAgreement.setCar(carRepository.getCarById(resultSet.getInt("fk_vehicle_id")));
                    leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                    leaseAgreement.setLeaseTypeFromString((resultSet.getString("lease_type")));
                    leaseAgreement.setLeasePrice(resultSet.getDouble("lease_price"));
                    leaseAgreement.setLeaseStartDate(resultSet.getTimestamp("lease_start_date"));
                    leaseAgreement.setLeaseEndDate(resultSet.getTimestamp("lease_end_date"));
                    leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                    leaseAgreement.setActive(resultSet.getBoolean("lease_active"));
                    if (resultSet.getDate("lease_end_date").before(Date.valueOf(LocalDate.now()))) {
                        leaseAgreement.setActive(false);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseAgreement;
    }

    // Returns a list of active (can also show inactive) lease agreements, used for display page and statistics
    public ArrayList<LeaseAgreement> getLeaseAgreementsByActiveStatus(boolean status){
        ArrayList<LeaseAgreement> leaseAgreementsByActiveStatus = new ArrayList<>();
        String sql = "SELECT * FROM lease_agreement WHERE lease_active = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    LeaseAgreement leaseAgreement = new LeaseAgreement();

                    leaseAgreement.setLeaseAgreementId(resultSet.getInt("lease_agreement_id"));
                    leaseAgreement.setCar(carRepository.getCarById(resultSet.getInt("fk_vehicle_id")));
                    leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                    leaseAgreement.setLeaseTypeFromString((resultSet.getString("lease_type")));
                    leaseAgreement.setLeasePrice(resultSet.getDouble("lease_price"));
                    leaseAgreement.setLeaseStartDate(resultSet.getTimestamp("lease_start_date"));
                    leaseAgreement.setLeaseEndDate(resultSet.getTimestamp("lease_end_date"));
                    leaseAgreement.setReturnLocation(resultSet.getString("return_location"));
                    leaseAgreement.setActive(resultSet.getBoolean("lease_active"));

                    leaseAgreementsByActiveStatus.add(leaseAgreement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseAgreementsByActiveStatus;
    }

    public void deleteLeaseAgreementById(int id) {
        String sql = "DELETE FROM lease_agreement WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLeaseAgreement(LeaseAgreement leaseAgreement) {
        String sql = "INSERT INTO lease_agreement (fk_vehicle_id, fk_customer_id, lease_type, lease_price, lease_start_date, lease_end_date, return_location, lease_active) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        leaseAgreement.setActive(true);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, leaseAgreement.getCar().getVehicleId());
                statement.setInt(2, leaseAgreement.getCustomer().getCustomerId());
                statement.setString(3, leaseAgreement.getLeaseType().name());
                statement.setDouble(4, leaseAgreement.getLeasePrice());
                statement.setTimestamp(5, leaseAgreement.getLeaseStartDate());
                statement.setTimestamp(6, leaseAgreement.getLeaseEndDate());
                statement.setString(7, leaseAgreement.getReturnLocation());
                statement.setBoolean(8, leaseAgreement.isActive());
                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLeaseAgreement(LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET fk_vehicle_id = ?, fk_customer_id = ?, lease_type = ?, lease_price = ?, lease_start_date = ?, lease_end_date = ?, return_location = ?, lease_active = ? WHERE lease_agreement_id =?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, leaseAgreement.getCar().getVehicleId());
                statement.setInt(2, leaseAgreement.getCustomer().getCustomerId());
                statement.setString(3, leaseAgreement.getLeaseType().name());
                statement.setDouble(4, leaseAgreement.getLeasePrice());
                statement.setTimestamp(5, leaseAgreement.getLeaseStartDate());
                statement.setTimestamp(6, leaseAgreement.getLeaseEndDate());
                statement.setString(7, leaseAgreement.getReturnLocation());
                statement.setBoolean(8, leaseAgreement.isActive());

                statement.setInt(9, leaseAgreement.getLeaseAgreementId());


                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
        }

    }

    public void setLeaseAgreementActive(LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET lease_active = ? WHERE lease_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1, leaseAgreement.isActive());
            statement.setInt(2, leaseAgreement.getLeaseAgreementId());

            statement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setLeaseAgreementInactive(LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET lease_active = ? WHERE lease_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1, false);
            statement.setInt(2, leaseAgreement.getLeaseAgreementId());

            statement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void checkLeaseEndDate(LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET lease_active = ? WHERE lease_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            if (leaseAgreement.getLeaseEndDate().before(Timestamp.valueOf(LocalDate.now().atStartOfDay())) || leaseAgreement.getLeaseEndDate().equals(Timestamp.valueOf(LocalDate.now().atStartOfDay()))) {
                statement.setBoolean(1, false);
            }else {
                statement.setBoolean(1, true);
            }

            statement.setInt(2, leaseAgreement.getLeaseAgreementId());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setEndDateNow(LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET lease_end_date = ? WHERE lease_agreement_id = ?";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            Timestamp today = Timestamp.valueOf(LocalDate.now().atStartOfDay());


            statement.setTimestamp(1, today);
            statement.setInt(2, leaseAgreement.getLeaseAgreementId());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}





