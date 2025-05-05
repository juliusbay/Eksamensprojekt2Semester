package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Controller.BookingController;
import org.example.eksamensprojekt2semester.Model.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Model model;

    @InjectMocks
    BookingController bookingController;



    @Test
    void getAllBookings() {
        //Arrange
        Booking booking = new Booking(
                1,
                1,
                "John Doe",
                "john.doe@example.com",
                "12345678",
                Booking.LeaseType.LIMITED,
                Date.valueOf("2025-06-01"),
                Date.valueOf("2025-06-10"),
                10000,
                false
        );

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        given(bookingRepository.getAllBookings()).willReturn(bookings);

        //Assert
        String result = bookingController.getAllBookings(model);

        //Act
        assertEquals("redirect:/dashboard", result);
        verify(bookingRepository).getAllBookings();
        verify(model).addAttribute("bookings", bookings);
    }

}