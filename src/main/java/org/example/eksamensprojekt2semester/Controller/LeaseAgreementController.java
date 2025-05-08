package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

@Controller
public class LeaseAgreementController {

    @Autowired
    LeaseAgreementRepository leaseAgreementRepository;


    //Create a leaseAgreement
    @PostMapping("/createBooking")
    public String createLeaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                       @RequestParam("fk_customer_id") int fkCustomerId,
                                       @RequestParam("lease_type")LeaseAgreement.LeaseType leaseType,
                                       @RequestParam("lease_price") int leasePrice,
                                       @RequestParam("lease_start_date")Date leaseStartDate,
                                       @RequestParam("lease_end_date") Date leaseEndDate,
                                       @RequestParam("return_location") String returnLocation,
                                       HttpSession session) throws SQLException {

    Object employee = session.getAttribute("loggedInUser");

    LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
            fkCustomerId, leaseType,
            leasePrice, leaseStartDate, leaseEndDate, returnLocation);
    leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        return "redirect:/dashboard";
    }

    //Update an existing leaseAgreement
    @PostMapping("saveUpdateLeaseAgreement")
    public String updateLeaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                       @RequestParam("fk_customer_id") int fkCustomerId,
                                       @RequestParam("lease_type")LeaseAgreement.LeaseType leaseType,
                                       @RequestParam("lease_price") int leasePrice,
                                       @RequestParam("lease_start_date")Date leaseStartDate,
                                       @RequestParam("lease_end_date") Date leaseEndDate,
                                       @RequestParam("return_location") String returnLocation,
                                       HttpSession session) throws SQLException {

        Object employee = session.getAttribute("loggedInUser");
        LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
                fkCustomerId, leaseType, leasePrice,
                leaseStartDate, leaseEndDate, returnLocation);
        leaseAgreementRepository.updateLeaseAgreement(leaseAgreement);

        return "redirect:/dashboard";
    }


    //Delete a specific leaseAgreement by its id
    @PostMapping("/dashboard")
    public String deleteLeaseAgreement(@RequestParam("lease_agreement_id") int leaseAgreementId,
                                       HttpSession session) throws SQLException {
        if (!isUserLoggedIn(session)){
            return "redirect:/";
        }
        leaseAgreementRepository.deleteLeaseAgreementById(leaseAgreementId);
        return "redirect:/dashboard";
    }

    //Get one specific leaseAgreement by its id
    @PostMapping("lease_agreement_details")
    public String getLeaseAgreementById(@RequestParam("lease_agreement_id") int leaseAgreementId,
                                        HttpSession session, Model model) throws SQLException {
        if (!isUserLoggedIn(session)){
            return "redirect:/";
        }

        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementById(leaseAgreementId);
        model.addAttribute("leaseAgreement", leaseAgreement);

        return "redirect:/lease_agreement_details" +leaseAgreementId;
    }

    //Get all leaseAgreements
    @GetMapping("/dashboard")
    public String getAllLeaseAgreements(Model model) {
        ArrayList<LeaseAgreement> leaseAgreements;
        leaseAgreements = leaseAgreementRepository.getAllLeaseAgreements();
        model.addAttribute("leaseAgreement", leaseAgreements);

        return "redirect:/dashboard";
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }


}
