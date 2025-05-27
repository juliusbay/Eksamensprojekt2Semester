package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.PurchaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Lavet af Frederik
@Repository
public class PurchaseAgreementRepository {


    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    public ArrayList<PurchaseAgreement> getAllPurchaseAgreements() {
        ArrayList<PurchaseAgreement> purchaseAgreements = new ArrayList<>();
        String sql = "SELECT * FROM purchase_agreement";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PurchaseAgreement purchaseAgreement = new PurchaseAgreement();
                purchaseAgreement.setPurchaseAgreementId(resultSet.getInt("purchase_agreement_id"));
                purchaseAgreement.setCar(carRepository.getCarById(resultSet.getInt(("fk_vehicle_id"))));
                purchaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(resultSet.getInt("fk_customer_id")));
                purchaseAgreement.setPaid(resultSet.getBoolean("paid"));
                purchaseAgreement.setCarPrice(resultSet.getDouble("car_price"));
                purchaseAgreements.add(purchaseAgreement);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return purchaseAgreements;
    }



        public PurchaseAgreement getPurchaseAgreementById(int id) {
        PurchaseAgreement purchaseAgreement = new PurchaseAgreement();
        String sql = "SELECT * FROM purchase_agreement WHERE purchase_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    purchaseAgreement.setPurchaseAgreementId(resultSet.getInt("purchase_agreement_id"));
                    purchaseAgreement.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    purchaseAgreement.setFkCustomerId(resultSet.getInt("fk_customer_id"));
                    purchaseAgreement.setCarPrice(resultSet.getDouble("car_price"));
                    purchaseAgreement.setPaid(resultSet.getBoolean("paid"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseAgreement;
    }

    public void deletePurchaseAgreementById(int id) {
        String sql = "DELETE FROM purchase_agreement WHERE purchase_agreement_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPurchaseAgreement(PurchaseAgreement purchaseAgreement) {
        String sql = "INSERT INTO purchase_agreement (fk_vehicle_id, fk_customer_id, car_price, paid) VALUES (?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, purchaseAgreement.getFkVehicleId());
            statement.setInt(2, purchaseAgreement.getFkCustomerId());
            statement.setDouble(3, purchaseAgreement.getCarPrice());
            statement.setBoolean(4, purchaseAgreement.isPaid());

            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updatePurchaseAgreement(PurchaseAgreement purchaseAgreement) {
        String sql = "UPDATE purchase_agreement SET fk_vehicle_id = ?, fk_customer_id = ?, car_price = ?, paid = ? WHERE purchase_agreement_id = ? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, purchaseAgreement.getFkVehicleId());
            statement.setInt(2, purchaseAgreement.getFkCustomerId());
            statement.setDouble(3, purchaseAgreement.getCarPrice());
            statement.setBoolean(4, purchaseAgreement.isPaid());
            statement.setInt(5, purchaseAgreement.getPurchaseAgreementId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

