package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Booking;
import org.example.eksamensprojekt2semester.Repository.BookingRepository;
import org.example.eksamensprojekt2semester.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @PostMapping("/createBooking")
    public String createBooking(@RequestParam("vehicle_id") int vehicle_id,
                                @RequestParam("customer_name") String custumer_name,
                                @RequestParam("customer_email") String customer_email,
                                @RequestParam("customer_phone") String customer_phone,
                                @RequestParam("lease_type") Booking.LeaseType LeaseType,
                                @RequestParam("lease_start_date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date lease_start_date,
                                @RequestParam("lease_end_date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date lease_end_date,
                                @RequestParam("contract_price") int contract_price,
                                @RequestParam("isAdvanceBuyer") boolean isAdvanceBuyer
                                ){
    java.util.Date convertStart = bookingService.dateFormatter(lease_start_date);
    java.sql.Date sqlDateStart=new java.sql.Date(convertStart.getTime());

    java.util.Date convertEnd = bookingService.dateFormatter(lease_end_date);
    java.sql.Date sqlDateEnd=new java.sql.Date(convertEnd.getTime());

    Booking booking = new Booking(vehicle_id, custumer_name, customer_email, customer_phone, LeaseType, sqlDateStart, sqlDateEnd, contract_price, isAdvanceBuyer);
    bookingRepository.createBooking(booking);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String getAllBookings(Model model) {


        ArrayList<Booking> bookings = bookingRepository.getAllBookings();
        model.addAttribute("bookings", bookings);

        return "redirect:/dashboard";
    }

    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }


}
