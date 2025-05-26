package org.example.eksamensprojekt2semester.Repository;
import org.example.eksamensprojekt2semester.Controller.LeaseAgreementController;
import org.example.eksamensprojekt2semester.Enum.LeaseType;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void getAllLeaseAgreements() {
        //Arrange
        LeaseAgreement leaseAgreement = new LeaseAgreement(
                1,
                1,
                LeaseType.LIMITED,
                10000,
                Timestamp.valueOf(LocalDate.of(2025, 6, 1).atStartOfDay()),
                Timestamp.valueOf(LocalDate.of(2025, 8, 1).atStartOfDay()),
                "guldbergsgade"
        );

        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        leaseAgreements.add(leaseAgreement);

        given(leaseAgreementRepository.getAllLeaseAgreements()).willReturn(leaseAgreements);

        //Act
        String result = leaseAgreementController.getAllLeaseAgreements(model);

        //Assert
        assertEquals("redirect:/dashboard", result);
        verify(leaseAgreementRepository).getAllLeaseAgreements();
        verify(model).addAttribute("leaseAgreements", leaseAgreements);
    }


    @Test
    @DisplayName("getAllLeaseAgreements alternate flow")
    public void getAllLeaseAgreementsAlternateFlow() {
        //Arrange

        LeaseAgreement leaseAgreement = new LeaseAgreement(
                1,
                1,
                LeaseType.LIMITED,
                10000,
                Timestamp.valueOf(LocalDate.of(2025, 6, 1).atStartOfDay()),
                Timestamp.valueOf(LocalDate.of(2025, 8, 1).atStartOfDay()),
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
        assertTrue(model.asMap().isEmpty(),"Model should be empty");
    }

}
