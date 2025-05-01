package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.BookingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BookingRepository {

    @Autowired
    DataSource dataSource;


    public BookingModel getBookingById(int id) {
        BookingModel booking = null;
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    booking = new BookingModel(
                            resultSet.getInt("booking_id"),
                            resultSet.getInt("vehicle_id"),
                            resultSet.getString("customer_name"),
                            resultSet.getString("customer_email"),
                            resultSet.getString("customer_phone"),
                            BookingModel.LeaseType.fromString(resultSet.getString("lease_type")),
                            resultSet.getDate("lease_start_date"),
                            resultSet.getDate("lease_end_date"),
                            resultSet.getDouble("contract_price"),
                            resultSet.getBoolean("advance_buyer")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public void createBooking(BookingModel booking) {
        String sql = "INSERT INTO bookings (vehicle_id, customer_name, customer_email, customer_phone, lease_type, lease_start_date, lease_end_date, contract_price, advance_buyer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, booking.getVehicle_id());
            statement.setString(2, booking.getCustomer_name());
            statement.setString(3, booking.getCustomer_email());
            statement.setString(4, booking.getCustomer_phone());
            statement.setString(5, booking.getLease_type().toString());
            statement.setDate(6, booking.getLease_start_date());
            statement.setDate(7, booking.getLease_end_date());
            statement.setDouble(8, booking.getContract_price());
            statement.setBoolean(9, booking.isAdvance_buyer());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteBookingById(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BookingModel> getAllBookings() {
        ArrayList<BookingModel> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                BookingModel booking = new BookingModel(
                        resultSet.getInt("booking_id"),
                        resultSet.getInt("vehicle_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("customer_phone"),
                        BookingModel.LeaseType.fromString(resultSet.getString("lease_type")),
                        resultSet.getDate("lease_start_date"),
                        resultSet.getDate("lease_end_date"),
                        resultSet.getDouble("contract_price"),
                        resultSet.getBoolean("advance_buyer")
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }


    public void updateBooking(BookingModel booking) {
        String sql = "UPDATE bookings SET vehicle_id = ?, customer_name = ?, customer_email = ?, customer_phone = ?, lease_type = ?, lease_start_date = ?, lease_end_date = ?, contract_price = ?, advance_buyer = ? WHERE booking_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, booking.getVehicle_id());
            statement.setString(2, booking.getCustomer_name());
            statement.setString(3, booking.getCustomer_email());
            statement.setString(4, booking.getCustomer_phone());
            statement.setString(5, booking.getLease_type().toString());
            statement.setDate(6, booking.getLease_start_date());
            statement.setDate(7, booking.getLease_end_date());
            statement.setDouble(8, booking.getContract_price());
            statement.setBoolean(9, booking.isAdvance_buyer());
            statement.setInt(10, booking.getBooking_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
