package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Service.LeaseAgreementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class LeaseAgreementCreateUnittest {
    // Mock dependencies

    @Mock
    LeaseAgreement leaseAgreement;

    @Mock
    LeaseAgreementRepository leaseAgreementRepository;

    @Mock
    Model model;

    @Mock
    LeaseAgreementService leaseService;

    @InjectMocks
    LeaseAgreementController leaseAgreementController;

    // Captures the LeaseAgreement object passed to the repository so we can inspect it



    @Test
    @DisplayName("createLeaseAgreement happyFlow")
    void createLeaseAgreementHappy() throws SQLException {
        // Arrange
        // mock input to simulate booking
        int vehicle_id = 1;
        int customer_id = 1;
        LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.LIMITED;
        int contract_price = 50000;
        Timestamp lease_start_date = Timestamp.valueOf("2025-06-01");
        Timestamp lease_end_date = Timestamp.valueOf("2025-06-10");
        String returnLocation = "Guldbergsgad";

        LeaseAgreement expectedLeaseAgreement = new LeaseAgreement(vehicle_id, customer_id, leaseType, contract_price,
                lease_start_date, lease_end_date, returnLocation);

        // Act
        // Call controller, as user would on site
        LeaseAgreement actualLeaseAgreement = leaseAgreementController.createLeaseAgreementMock(vehicle_id, customer_id, leaseType, contract_price, lease_start_date, lease_end_date, returnLocation);


        // Assert


        // Compare each field individually to ensure the booking was created correctly
        assertEquals(expectedLeaseAgreement.getFkVehicleId(), actualLeaseAgreement.getFkVehicleId());
        assertEquals(expectedLeaseAgreement.getFkCustomerId(), actualLeaseAgreement.getFkCustomerId());
        assertEquals(expectedLeaseAgreement.getLeaseType(), actualLeaseAgreement.getLeaseType());
        assertEquals(expectedLeaseAgreement.getLeasePrice(), actualLeaseAgreement.getLeasePrice());
        assertEquals(expectedLeaseAgreement.getLeaseStartDate(), actualLeaseAgreement.getLeaseStartDate());
        assertEquals(expectedLeaseAgreement.getLeaseEndDate(), actualLeaseAgreement.getLeaseEndDate());
        assertEquals(expectedLeaseAgreement.getReturnLocation(), actualLeaseAgreement.getReturnLocation());
    }

    @Test
    @DisplayName("createLeaseAgreement exceptionFlow")
    public void createLeaseAgreementExceptionNoNegativePrice() throws SQLException {
        //Arrange

        int vehicle_id = 1;
        int customer_id = 1;
        LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.LIMITED;
        int leasePrice = -50000;
        Timestamp lease_start_date = Timestamp.valueOf("2025-06-01");
        Timestamp lease_end_date = Timestamp.valueOf("2025-12-10");
        String returnLocation = "Guldbergsgad";

        LeaseAgreement expectedLeaseAgreement = new LeaseAgreement(vehicle_id, customer_id, leaseType, leasePrice,
                lease_start_date, lease_end_date, returnLocation);





        //Act

        LeaseAgreement actualLeaseAgreement = leaseAgreementController.createLeaseAgreementMock(vehicle_id, customer_id, leaseType, leasePrice, lease_start_date, lease_end_date, returnLocation);
        System.out.println(actualLeaseAgreement);

        //Assert
        assertEquals(expectedLeaseAgreement.getFkVehicleId(), actualLeaseAgreement.getFkVehicleId());
        assertEquals(expectedLeaseAgreement.getFkCustomerId(), actualLeaseAgreement.getFkCustomerId());
        assertEquals(expectedLeaseAgreement.getLeaseType(), actualLeaseAgreement.getLeaseType());
        assertEquals(expectedLeaseAgreement.getLeasePrice(), actualLeaseAgreement.getLeasePrice());
        assertEquals(expectedLeaseAgreement.getLeaseStartDate(), actualLeaseAgreement.getLeaseStartDate());
        assertEquals(expectedLeaseAgreement.getLeaseEndDate(), actualLeaseAgreement.getLeaseEndDate());
        assertEquals(expectedLeaseAgreement.getReturnLocation(), actualLeaseAgreement.getReturnLocation());
    }


    @Test
    @DisplayName("createLeaseAgreement exceptionFlow")
    public void createLeaseAgreementException120DaysException() throws SQLException {
        //Arrange

        int vehicle_id = 1;
        int customer_id = 1;
        LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.UNLIMITED;
        int leasePrice = 50000;
        Timestamp lease_start_date = Timestamp.valueOf("2025-06-01");
        Timestamp lease_end_date = Timestamp.valueOf("2025-8-10");
        String returnLocation = "Guldbergsgad";

        LeaseAgreement expectedLeaseAgreement = new LeaseAgreement(vehicle_id, customer_id, leaseType, leasePrice,
                lease_start_date, lease_end_date, returnLocation);





        //Act

        LeaseAgreement actualLeaseAgreement = leaseAgreementController.createLeaseAgreementMock(vehicle_id, customer_id, leaseType, leasePrice, lease_start_date, lease_end_date, returnLocation);
        System.out.println(actualLeaseAgreement);

        //Assert
        assertEquals(expectedLeaseAgreement.getFkVehicleId(), actualLeaseAgreement.getFkVehicleId());
        assertEquals(expectedLeaseAgreement.getFkCustomerId(), actualLeaseAgreement.getFkCustomerId());
        assertEquals(expectedLeaseAgreement.getLeaseType(), actualLeaseAgreement.getLeaseType());
        assertEquals(expectedLeaseAgreement.getLeasePrice(), actualLeaseAgreement.getLeasePrice());
        assertEquals(expectedLeaseAgreement.getLeaseStartDate(), actualLeaseAgreement.getLeaseStartDate());
        assertEquals(expectedLeaseAgreement.getLeaseEndDate(), actualLeaseAgreement.getLeaseEndDate());
        assertEquals(expectedLeaseAgreement.getReturnLocation(), actualLeaseAgreement.getReturnLocation());
    }


}