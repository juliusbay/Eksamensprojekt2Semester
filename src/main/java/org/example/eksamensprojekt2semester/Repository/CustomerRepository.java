package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CustomerRepository {

    @Autowired
    DataSource dataSource;

    //Method for getting buyer by their BuyerID
    public Customer getCustomerByCustomerId(int id){
        Customer customer = new Customer();
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        customer.setCustomerId(resultSet.getInt("customer_id"));
                        customer.setFirstName(resultSet.getString("first_name"));
                        customer.setLastName(resultSet.getString("last_name"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setPhoneNumber(resultSet.getInt("phone_number"));
                        customer.setAddress(resultSet.getString("address"));
                        customer.setCity(resultSet.getString("city"));
                        customer.setPostalCode(resultSet.getInt("postal_code"));
                        customer.setCprNumber(resultSet.getInt("cpr_number"));
                        customer.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                    }
                }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return customer;
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhoneNumber(resultSet.getInt("phone_number"));
                customer.setAddress(resultSet.getString("address"));
                customer.setCity(resultSet.getString("city"));
                customer.setPostalCode(resultSet.getInt("postal_code"));
                customer.setCprNumber(resultSet.getInt("cpr_number"));
                customer.setFkVehicleId(resultSet.getInt("fk_vehicle_id"));
                customers.add(customer);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    //Method for creating customer.
    public void createCustomer(Customer customer){
        String sql = "INSERT INTO customer (first_name, last_name, email, phone_number, address, city, postal_code, cpr_number, fk_vehicle_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setInt(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());
            statement.setString(6, customer.getCity());
            statement.setInt(7, customer.getPostalCode());
            statement.setInt(8, customer.getCprNumber());
            statement.setInt(9, customer.getFkVehicleId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Method for updating customer details
    public void updateCustomer(Customer customer){
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, city = ?, postal_code = ?, cpr_number = ?, fk_vehicle_id = ? " +
                "WHERE customer_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setInt(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());
            statement.setString(6, customer.getCity());
            statement.setInt(7, customer.getPostalCode());
            statement.setInt(8, customer.getCprNumber());
            statement.setInt(9, customer.getFkVehicleId());

            statement.setInt(6, customer.getCustomerId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Method for deleting buyer by their buyerId
    public void deleteCustomerFromCustomerId(int id){
        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
