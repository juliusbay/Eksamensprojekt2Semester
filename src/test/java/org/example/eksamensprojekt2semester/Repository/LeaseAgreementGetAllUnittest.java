package org.example.eksamensprojekt2semester.Repository;
import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class LeaseAgreementGetAllUnittest {
    @Mock
    private LeaseAgreementRepository leaseAgreementRepository;

    @Mock
    private Model model;

    @InjectMocks
    LeaseAgreementController leaseAgreementController;



    @Test
    @DisplayName("getAllLeaseAgreements happyFlow")
    void getAllLeaseAgreements() throws SQLException {
        //Arrange

        LeaseAgreement leaseAgreement = new LeaseAgreement(
                1,
                1,
                LeaseAgreement.LeaseType.LIMITED,
                10000,
                Date.valueOf("2025-06-01"),
                Date.valueOf("2025-06-10"),
                "guldbergsgade"
        );

        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        leaseAgreements.add(leaseAgreement);

        given(leaseAgreementRepository.getAllLeaseAgreements()).willReturn(leaseAgreements);

        //Assert
        String result = leaseAgreementController.getAllLeaseAgreements(model);

        //Act
        assertEquals("redirect:/dashboard", result);
        verify(leaseAgreementRepository).getAllLeaseAgreements();
        verify(model).addAttribute("leaseAgreements", leaseAgreements);
    }


    @Test
    @DisplayName("getAllLeaseAgreements exceptionFlow")
    public void getAllLeaseAgreementsException() throws SQLException {
        //Arrange

        LeaseAgreement leaseAgreement = new LeaseAgreement(
                1,
                1,
                LeaseAgreement.LeaseType.LIMITED,
                10000,
                Date.valueOf("2025-06-01"),
                Date.valueOf("2025-06-10"),
                "guldbergsgade"
        );

        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        leaseAgreements.add(leaseAgreement);


        given(leaseAgreementRepository.getAllLeaseAgreements()).willReturn(leaseAgreements);

        //Assert
        String result = leaseAgreementController.getAllLeaseAgreements(model);

        //Act
        assertEquals("redirect:/dashboard", result);
        verify(leaseAgreementRepository).getAllLeaseAgreements();
        verify(model).addAttribute("No models", leaseAgreements);


    }

}
