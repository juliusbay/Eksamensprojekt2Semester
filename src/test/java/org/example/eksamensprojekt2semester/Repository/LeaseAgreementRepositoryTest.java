package org.example.eksamensprojekt2semester.Repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LeaseAgreementRepositoryTest {
/*
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Model model;

    @InjectMocks
    BookingController bookingController;



    @Test
    void getAllBookings() {
        //Arrange

        LeaseAgreement leaseAgreement = new LeaseAgreement(
                1,
                1,
                "John Doe",
                "john.doe@example.com",
                "12345678",
                LeaseAgreement.LeaseType.LIMITED,
                Date.valueOf("2025-06-01"),
                Date.valueOf("2025-06-10"),
                10000,
                false
        );

        ArrayList<LeaseAgreement> leaseAgreements = new ArrayList<>();
        leaseAgreements.add(leaseAgreement);

        given(bookingRepository.getAllBookings()).willReturn(leaseAgreements);

        //Assert
        String result = bookingController.getAllBookings(model);

        //Act
        assertEquals("redirect:/dashboard", result);
        verify(bookingRepository).getAllBookings();
        verify(model).addAttribute("bookings", leaseAgreements);
    }
*/
}