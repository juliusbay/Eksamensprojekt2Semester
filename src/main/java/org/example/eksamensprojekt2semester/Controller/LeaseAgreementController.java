package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Car;
import org.example.eksamensprojekt2semester.Model.Customer;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.example.eksamensprojekt2semester.Repository.CustomerRepository;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.example.eksamensprojekt2semester.Service.LeaseAgreementService;
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

    @Autowired
    LeaseAgreementService leaseAgreementService;

    @Autowired
    CarRepository carRepository;
    @Autowired
    private CustomerRepository customerRepository;


    //Create a leaseAgreement
    @PostMapping("/createLease")
    public String createLeaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                       @RequestParam("fk_customer_id") int fkCustomerId,
                                       @RequestParam("lease_type")String leaseTypeString,
                                       @RequestParam("lease_price") int leasePrice,
                                       @RequestParam("lease_start_date")Date leaseStartDate,
                                       @RequestParam("lease_end_date") Date leaseEndDate,
                                       @RequestParam("return_location") String returnLocation,
                                       HttpSession session) throws SQLException {

    Object employee = session.getAttribute("loggedInUser");

    LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.valueOf(leaseTypeString.toUpperCase());

    LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
            fkCustomerId, leaseType,
            leasePrice, leaseStartDate, leaseEndDate, returnLocation);


        leaseAgreementService.noNegativePriceLease(leaseAgreement);
        leaseAgreementService.isEndDateBeforeStartDate(leaseAgreement);

        leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(fkCustomerId));

    leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
    leaseAgreementRepository.setLeaseAgreementActive(leaseAgreement);


    carRepository.getCarById(fkVehicleId).setRentedOut(true);
        return "redirect:/";
    }


    @GetMapping("/createLeasePage")
    public String showCreateLeasePage(Model model, HttpSession session) throws SQLException {
        Object employee = session.getAttribute("loggedInUser");
        model.addAttribute("cars",carRepository.getAllCars());
        model.addAttribute("customers",customerRepository.getAllCustomers());
        model.addAttribute("leaseTypes", LeaseAgreement.LeaseType.values());
        return "createLeasePage";
    }








    //Mock create lease without Http
    public LeaseAgreement createLeaseAgreementMock( int fkVehicleId,
                                        int fkCustomerId,
                                       LeaseAgreement.LeaseType leaseType,
                                        int leasePrice,
                                       Date leaseStartDate,
                                        Date leaseEndDate, String returnLocation) throws SQLException {

        LeaseAgreementService leaseService = new LeaseAgreementService();

        LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
                fkCustomerId, leaseType,
                leasePrice, leaseStartDate, leaseEndDate, returnLocation);


        leaseService.noNegativePriceLease(leaseAgreement);

        leaseService.isEndDateBeforeStartDate(leaseAgreement);

        leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        return leaseAgreement;
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
    @PostMapping("/deleteLease")
    public String deleteLeaseAgreement(@RequestParam("lease_agreement_id") int leaseAgreementId,
                                       HttpSession session) throws SQLException {
        if (!isUserLoggedIn(session)){
            return "redirect:/";
        }
        leaseAgreementRepository.deleteLeaseAgreementById(leaseAgreementId);
        return "redirect:/";
    }

    //Get one specific leaseAgreement by its id
    @GetMapping("/leaseDetails")
    public String getLeaseAgreementById(@RequestParam("lease_agreement_id") int leaseAgreementId,
                                        HttpSession session, Model model) throws SQLException {

        /*if (!isUserLoggedIn(session)){
            return "redirect:/";
        }*/

        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementById(leaseAgreementId);
        model.addAttribute("leaseAgreement", leaseAgreement);

        return "show-leases-page";
    }

    //Get all leaseAgreements
    @GetMapping("/bingo")
    public String getAllLeaseAgreements(Model model) {
        ArrayList<LeaseAgreement> leaseAgreements= leaseAgreementRepository.getAllLeaseAgreements();
        model.addAttribute("leaseAgreements", leaseAgreements);


        return "redirect:/dashboard";
    }
    @GetMapping("/toggleLeaseStatus")
    public String toggleLeaseStatus(@RequestParam("lease_agreement_id") int leaseAgreementId,HttpSession session) throws SQLException {
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementById(leaseAgreementId);
        leaseAgreement.setActive(!leaseAgreement.isActive());
        leaseAgreementRepository.setLeaseAgreementActive(leaseAgreement);

        return "redirect:/leaseDetails?lease_agreement_id=" + leaseAgreementId;

    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }




}
