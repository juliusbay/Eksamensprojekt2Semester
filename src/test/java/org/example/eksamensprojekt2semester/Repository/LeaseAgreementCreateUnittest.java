package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Enum.LeaseType;
import org.example.eksamensprojekt2semester.Model.Car;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//Lavet af Frederik
@ExtendWith(MockitoExtension.class)
public class LeaseAgreementCreateUnittest {
    // Mock dependencies
    @InjectMocks
    LeaseAgreementController leaseAgreementController;

    @Mock
    LeaseAgreementRepository leaseAgreementRepository;

    @Mock
    LeaseAgreementService leaseAgreementService;

    @Mock
    CarRepository carRepository;


    @Test
    @DisplayName("createLeaseAgreement happyFlow")
    void createLeaseAgreementHappy() {
        // Arrange
        int vehicle_id = 1;
        int customer_id = 1;
        LeaseType leaseType = LeaseType.LIMITED;
        double lease_price = 50000;
        Timestamp lease_start_date = Timestamp.valueOf(LocalDate.of(2025, 6, 1).atStartOfDay());
        Timestamp lease_end_date = Timestamp.valueOf(LocalDate.of(2025, 8, 1).atStartOfDay());
        String returnLocation = "Guldbergsgad";

        LeaseAgreement expectedLeaseAgreement = new LeaseAgreement(
                vehicle_id, customer_id, leaseType, lease_price, lease_start_date, lease_end_date, returnLocation
        );

        Car mockCar = new Car();
        mockCar.setVehicleId(vehicle_id);

        // Mock method calls
        when(carRepository.getCarById(vehicle_id)).thenReturn(mockCar);
        //This is to ensure, that we dont use the actual datasource
        doNothing().when(leaseAgreementRepository).createLeaseAgreement(any());

        // Act
        LeaseAgreement actual = leaseAgreementController.createLeaseAgreementMock(
                vehicle_id, customer_id, leaseType, lease_price, lease_start_date, lease_end_date, returnLocation
        );

        // Assert
        assertEquals(expectedLeaseAgreement.getFkVehicleId(), actual.getFkVehicleId());
        assertEquals(expectedLeaseAgreement.getFkCustomerId(), actual.getFkCustomerId());
        assertEquals(expectedLeaseAgreement.getLeaseType(), actual.getLeaseType());
        assertEquals(expectedLeaseAgreement.getLeasePrice(), actual.getLeasePrice());
        assertEquals(expectedLeaseAgreement.getLeaseStartDate(), actual.getLeaseStartDate());
        assertEquals(expectedLeaseAgreement.getLeaseEndDate(), actual.getLeaseEndDate());
        assertEquals(expectedLeaseAgreement.getReturnLocation(), actual.getReturnLocation());
    }

    @Test
    @DisplayName("createLeaseAgreement exceptionFlow")
    public void createLeaseAgreementExceptionNoNegativePrice() {
        //Arrange

        int vehicle_id = 1;
        int customer_id = 1;
        LeaseType leaseType = LeaseType.LIMITED;
        double leasePrice = -50000;
        Timestamp lease_start_date = Timestamp.valueOf(LocalDate.of(2025, 6, 1).atStartOfDay());
        Timestamp lease_end_date = Timestamp.valueOf(LocalDate.of(2025, 8, 1).atStartOfDay());
        String returnLocation = "Guldbergsgad";


        //This method we acutally want to call, to recieve the proper exception
        doCallRealMethod().when(leaseAgreementService).noNegativePriceLease(any());

        // Act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> leaseAgreementController.createLeaseAgreementMock(
                        vehicle_id, customer_id, leaseType, leasePrice, lease_start_date, lease_end_date, returnLocation
                )
        );

        // Assert
        assertEquals("LeasePrice must be greater than 0",thrown.getMessage());

    }

    @Test
    @DisplayName("createLeaseAgreement exceptionFlow")
    public void createLeaseAgreementException120DaysException() {
        //Arrange

        int vehicle_id = 1;
        int customer_id = 1;
        LeaseType leaseType = LeaseType.UNLIMITED;
        double leasePrice = 50000;
        Timestamp lease_start_date =  Timestamp.valueOf(LocalDate.of(2025, 6, 1).atStartOfDay());
        Timestamp lease_end_date =  Timestamp.valueOf(LocalDate.of(2025, 8, 1).atStartOfDay());
        String returnLocation = "Guldbergsgad";


        //This method we acutally want to call, to recieve the proper exception
        doCallRealMethod().when(leaseAgreementService).minimum120daysAgreement(any());

        // Act
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> leaseAgreementController.createLeaseAgreementMock(
                        vehicle_id, customer_id, leaseType, leasePrice, lease_start_date, lease_end_date, returnLocation
                )
        );

        // Assert
        assertEquals("Unlimited lease agreements must be at least 120 days long.",thrown.getMessage());
    }
}