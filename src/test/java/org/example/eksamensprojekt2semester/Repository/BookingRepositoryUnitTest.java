package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Controller.BookingController;
import org.example.eksamensprojekt2semester.Model.Booking;
import org.example.eksamensprojekt2semester.Service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingRepositoryUnitTest {
    // Mock dependencies
    @Mock
    BookingRepository bookingRepository;

    @Mock
    Model model;

    @InjectMocks
    BookingController bookingController;

    @Mock
    private BookingService bookingService;

    // Captures the Booking object passed to the repository so we can inspect it
    @Captor
    ArgumentCaptor<Booking> bookingCaptor;

    @BeforeEach
    void setup() {
        // Initializes mocks before each test runs
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking() {
        // Arrange
        // mock input to simulate booking
        int vehicle_id = 1;
        String customer_name = "John Doe";
        String customer_email = "john.doe@example.com";
        String customer_phone = "12345678";
        Booking.LeaseType leaseType = Booking.LeaseType.LIMITED;
        Date lease_start_date = Date.valueOf("2025-06-01");
        Date lease_end_date = Date.valueOf("2025-06-10");
        int contract_price = 500;
        boolean isAdvanceBuyer = true;

        // convert time
        java.util.Date formattedStart = new java.util.Date(lease_start_date.getTime());
        java.util.Date formattedEnd = new java.util.Date(lease_end_date.getTime());

        // Mock the service method so it returns the formatted dates we expect
        when(bookingService.dateFormatter(lease_start_date)).thenReturn(formattedStart);
        when(bookingService.dateFormatter(lease_end_date)).thenReturn(formattedEnd);

        // Booking after format
        Booking expectedBooking = new Booking(vehicle_id, customer_name, customer_email, customer_phone, leaseType,
                new java.sql.Date(formattedStart.getTime()), new java.sql.Date(formattedEnd.getTime()), contract_price, isAdvanceBuyer);

        // Act
        // Call controller, as user would on site
        String result = bookingController.createBooking(vehicle_id, customer_name, customer_email, customer_phone,
                leaseType, lease_start_date, lease_end_date, contract_price, isAdvanceBuyer);

        // Assert
        // Capture the booking object that was passed to the repository
        verify(bookingRepository).createBooking(bookingCaptor.capture());
        Booking actualBooking = bookingCaptor.getValue();

        // Compare each field individually to ensure the booking was created correctly
        assertEquals(expectedBooking.getVehicleId(), actualBooking.getVehicleId());
        assertEquals(expectedBooking.getCustomerName(), actualBooking.getCustomerName());
        assertEquals(expectedBooking.getCustomerEmail(), actualBooking.getCustomerEmail());
        assertEquals(expectedBooking.getCustomerPhone(), actualBooking.getCustomerPhone());
        assertEquals(expectedBooking.getLeaseType(), actualBooking.getLeaseType());
        assertEquals(expectedBooking.getContractPrice(), actualBooking.getContractPrice());
        assertEquals(expectedBooking.isAdvanceBuyer(), actualBooking.isAdvanceBuyer());

    }

}