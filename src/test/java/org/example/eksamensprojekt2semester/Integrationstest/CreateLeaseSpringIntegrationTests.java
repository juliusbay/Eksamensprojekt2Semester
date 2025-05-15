package org.example.eksamensprojekt2semester.Integrationstest;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Model.Customer;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Repository.CustomerRepository;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Date;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateLeaseSpringIntegrationTests {

    @MockitoBean
    private LeaseAgreementRepository leaseAgreementRepository;

    @Autowired
    private LeaseAgreementController leaseAgreementController;

    @MockitoBean
    private CustomerRepository customerRepository;

    @Test
    void createLeaseAgreementTest() throws SQLException {

        // Assumption
        LeaseAgreement leaseAgreement = new LeaseAgreement();
        leaseAgreement.setLeaseAgreementId(0);
        leaseAgreement.setFkVehicleId(1);
        leaseAgreement.setFkCustomerId(1);
        leaseAgreement.setLeaseType(LeaseAgreement.LeaseType.UNLIMITED);
        leaseAgreement.setLeasePrice(1000);
        leaseAgreement.setLeaseStartDate(Date.valueOf("2025-01-01"));
        leaseAgreement.setLeaseEndDate(Date.valueOf("2025-01-02"));
        leaseAgreement.setReturnLocation("Test");
        HttpSession testSession = mock(HttpSession.class);
        Object testEmployee = new Object();


        //Execution
        leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        when(testSession.getAttribute("loggedInUser")).thenReturn(testEmployee);

        Customer mockCustomer = mock(Customer.class);
        when(customerRepository.getCustomerByCustomerId(1)).thenReturn(mockCustomer);

        leaseAgreementController.createLeaseAgreement(1,1, "Unlimited",1000,Date.valueOf("2025-01-01"),Date.valueOf("2025-01-02"),"Test",testSession);


        //Validation
        verify(testSession).getAttribute("loggedInUser");
        verify(leaseAgreementRepository).createLeaseAgreement(leaseAgreement);


    }

}
