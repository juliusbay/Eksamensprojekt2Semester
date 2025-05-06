package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.sql.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaseAgreementRepositoryUnitTest {
    // Mock dependencies
    @Mock
    BookingRepository bookingRepository;

    @Mock
    Model model;

    @InjectMocks
    LeaseAgreementController leaseAgreementController;

    @Mock
    private BookingService bookingService;

    // Captures the Booking object passed to the repository so we can inspect it
    @Captor
    ArgumentCaptor<LeaseAgreement> bookingCaptor;

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
        LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.LIMITED;
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
        LeaseAgreement expectedLeaseAgreement = new LeaseAgreement(vehicle_id, customer_name, customer_email, customer_phone, leaseType,
                new java.sql.Date(formattedStart.getTime()), new java.sql.Date(formattedEnd.getTime()), contract_price, isAdvanceBuyer);

        // Act
        // Call controller, as user would on site
        String result = leaseAgreementController.createBooking(vehicle_id, customer_name, customer_email, customer_phone,
                leaseType, lease_start_date, lease_end_date, contract_price, isAdvanceBuyer);

        // Assert
        // Capture the booking object that was passed to the repository
        verify(bookingRepository).createBooking(bookingCaptor.capture());
        LeaseAgreement actualLeaseAgreement = bookingCaptor.getValue();

        // Compare each field individually to ensure the booking was created correctly
        assertEquals(expectedLeaseAgreement.getVehicleId(), actualLeaseAgreement.getVehicleId());
        assertEquals(expectedLeaseAgreement.getCustomerName(), actualLeaseAgreement.getCustomerName());
        assertEquals(expectedLeaseAgreement.getCustomerEmail(), actualLeaseAgreement.getCustomerEmail());
        assertEquals(expectedLeaseAgreement.getCustomerPhone(), actualLeaseAgreement.getCustomerPhone());
        assertEquals(expectedLeaseAgreement.getLeaseType(), actualLeaseAgreement.getLeaseType());
        assertEquals(expectedLeaseAgreement.getContractPrice(), actualLeaseAgreement.getContractPrice());
        assertEquals(expectedLeaseAgreement.isAdvanceBuyer(), actualLeaseAgreement.isAdvanceBuyer());

    }

}