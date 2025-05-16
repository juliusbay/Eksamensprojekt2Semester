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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateLeaseSpringIntegrationTests {

    @Autowired
    private LeaseAgreementRepository leaseAgreementRepository;

    @Autowired
    private LeaseAgreementController leaseAgreementController;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void createLeaseAgreementTest() throws SQLException {

        // Assumption
        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementById(1);
        HttpSession testSession = mock(HttpSession.class);
        Object testEmployee = new Object();
        Customer customer = leaseAgreement.getCustomer();


        //Execution
        leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        when(testSession.getAttribute("loggedInUser")).thenReturn(testEmployee);
        customerRepository.getCustomerByCustomerId(1);

        leaseAgreementController.createLeaseAgreement(2,1, "Unlimited",1000,Date.valueOf("2025-01-01"),Date.valueOf("2025-01-02"),"Test",testSession);


        //Validation
        assertNotNull(leaseAgreement);
        assertNotNull(leaseAgreement.getCustomer());
        assertNotNull(testSession.getAttribute("loggedInUser"));
        assertEquals(1,leaseAgreement.getLeaseAgreementId());
        assertEquals(LeaseAgreement.LeaseType.UNLIMITED,leaseAgreement.getLeaseType());


    }

}
