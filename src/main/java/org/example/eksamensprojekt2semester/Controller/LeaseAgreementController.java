package org.example.eksamensprojekt2semester.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class LeaseAgreementController {
    /*
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @PostMapping("/createBooking")
    public String createBooking(@RequestParam("vehicle_id") int vehicle_id,
                                @RequestParam("customer_name") String custumer_name,
                                @RequestParam("customer_email") String customer_email,
                                @RequestParam("customer_phone") String customer_phone,
                                @RequestParam("lease_type") LeaseAgreement.LeaseType LeaseType,
                                @RequestParam("lease_start_date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date lease_start_date,
                                @RequestParam("lease_end_date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date lease_end_date,
                                @RequestParam("contract_price") int contract_price,
                                @RequestParam("isAdvanceBuyer") boolean isAdvanceBuyer
                                ){
    java.util.Date convertStart = bookingService.dateFormatter(lease_start_date);
    java.sql.Date sqlDateStart=new java.sql.Date(convertStart.getTime());

    java.util.Date convertEnd = bookingService.dateFormatter(lease_end_date);
    java.sql.Date sqlDateEnd=new java.sql.Date(convertEnd.getTime());

    LeaseAgreement leaseAgreement = new LeaseAgreement(vehicle_id, custumer_name, customer_email, customer_phone, LeaseType, sqlDateStart, sqlDateEnd, contract_price, isAdvanceBuyer);
    bookingRepository.createBooking(leaseAgreement);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String getAllBookings(Model model) {


        ArrayList<LeaseAgreement> leaseAgreements = bookingRepository.getAllBookings();
        model.addAttribute("bookings", leaseAgreements);

        return "redirect:/dashboard";
    }

    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }

*/
}
