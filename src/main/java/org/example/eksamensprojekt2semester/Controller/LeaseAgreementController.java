package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Repository.*;
import org.example.eksamensprojekt2semester.Service.LeaseAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ConditionReportRepository conditionReportRepository;

    @Autowired
    CarModelRepository carModelRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PurchaseAgreementRepository purchaseAgreementRepository;


    //Create a leaseAgreement
    @PostMapping("/createLease")
    public String createLeaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                       @RequestParam("fk_customer_id") int fkCustomerId,
                                       @RequestParam("lease_type")String leaseTypeString,
                                       @RequestParam("lease_price") double leasePrice,
                                       @RequestParam("lease_start_date")Date leaseStartDate,
                                       @RequestParam("lease_end_date") Date leaseEndDate,
                                       @RequestParam("return_location") String returnLocation,
                                       HttpSession session, Model model) throws SQLException {

        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }


        Timestamp leaseStartDateTS = Timestamp.valueOf(leaseStartDate.toString()+ " 00:00:00");
        Timestamp leaseEndDateTS = Timestamp.valueOf(leaseEndDate.toString()+ " 00:00:00");
    //Following try catch block, is to show an error message instead of white label
    try{
        LeaseAgreement.LeaseType leaseType = LeaseAgreement.LeaseType.fromString(leaseTypeString);

        LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
                fkCustomerId, leaseType,
                leasePrice, leaseStartDateTS, leaseEndDateTS, returnLocation);

        leaseAgreementService.maximum120daysAgreement(leaseAgreement);
        leaseAgreementService.isEndDateBeforeStartDate(leaseAgreement);
        leaseAgreementService.minimum120daysAgreement(leaseAgreement);
        leaseAgreement.setCar(carRepository.getCarById(fkVehicleId));
        leaseAgreement.setCustomer(customerRepository.getCustomerByCustomerId(fkCustomerId));

        leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        leaseAgreementRepository.setLeaseAgreementActive(leaseAgreement);


        carRepository.getCarById(fkVehicleId).setStatusFromString("RENTED");
        return "redirect:/";
    } catch (Exception e){
        //We "trap" the corrosponding error message within a model, and send it to Thymeleaf it wil show the activated exception message

        model.addAttribute("errorMessage", e.getMessage());
        //we activate this method, as we would on page controller
        reloadLeaseFormData(model);

        return "dashboard";
        }
    }
    //This method is the same as the pageController. We retrieve all of the tables again, after an excpetion has been met.
    //If we dont do this, all of the tables will be modelless
    private void reloadLeaseFormData(Model model) throws SQLException {
        model.addAttribute("cars", carRepository.getAllCars());
        model.addAttribute("customers", customerRepository.getAllCustomers());
        model.addAttribute("leaseTypes", LeaseAgreement.LeaseType.values());
        model.addAttribute("leaseAgreements", leaseAgreementRepository.getAllLeaseAgreements());
        model.addAttribute("carModels", carModelRepository.getAllCarModels());
        model.addAttribute("conditionReport", conditionReportRepository.getAllConditionReports());
        model.addAttribute("employees", employeeRepository.getAllEmployees());
        model.addAttribute("purchaseAgreements", purchaseAgreementRepository.getAllPurchaseAgreements());

        Map<Integer, LeaseAgreement> leaseAgreementMap = new HashMap<>();
        for (LeaseAgreement la : leaseAgreementRepository.getAllLeaseAgreements()) {
            leaseAgreementMap.put(la.getFkVehicleId(), la);
        }
        model.addAttribute("leaseAgreementMap", leaseAgreementMap);
    }

    @GetMapping("/createLeasePage")
    public String showCreateLeasePage(Model model, HttpSession session) throws SQLException {

        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("cars",carRepository.getAllCars());
        model.addAttribute("customers",customerRepository.getAllCustomers());
        model.addAttribute("leaseTypes", LeaseAgreement.LeaseType.values());
        return "dashboard";
    }

    //Mock create lease without Http
    public LeaseAgreement createLeaseAgreementMock( int fkVehicleId,
                                        int fkCustomerId,
                                       LeaseAgreement.LeaseType leaseType,
                                        double leasePrice,
                                                    Timestamp leaseStartDate,
                                                    Timestamp leaseEndDate,
                                                    String returnLocation) throws SQLException {


        LeaseAgreement leaseAgreement = new LeaseAgreement(fkVehicleId,
                fkCustomerId, leaseType,
                leasePrice, leaseStartDate, leaseEndDate, returnLocation);


        leaseAgreementService.minimum120daysAgreement(leaseAgreement);
        leaseAgreementService.noNegativePriceLease(leaseAgreement);
        leaseAgreementService.isEndDateBeforeStartDate(leaseAgreement);



        carRepository.getCarById(fkVehicleId).setStatusFromString("RENTED");
        leaseAgreementRepository.createLeaseAgreement(leaseAgreement);
        return leaseAgreement;
    }


    //Update an existing leaseAgreement
    @PostMapping("saveUpdateLeaseAgreement")
    public String updateLeaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                       @RequestParam("fk_customer_id") int fkCustomerId,
                                       @RequestParam("lease_type")LeaseAgreement.LeaseType leaseType,
                                       @RequestParam("lease_price") double leasePrice,
                                       @RequestParam("lease_start_date")Timestamp leaseStartDate,
                                       @RequestParam("lease_end_date") Timestamp leaseEndDate,
                                       @RequestParam("return_location") String returnLocation,
                                       HttpSession session) throws SQLException {

        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }

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

        if (!isUserLoggedIn(session)) {
            return "redirect:/login";
        }

        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementById(leaseAgreementId);
        leaseAgreementRepository.checkLeaseEndDate(leaseAgreement);
        model.addAttribute("leaseAgreement", leaseAgreement);

        return "show-leases-page";
    }

    //Get all leaseAgreements
    @GetMapping("/bingo")
    public String getAllLeaseAgreements(Model model) throws SQLException {
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
        leaseAgreementRepository.setLeaseAgreementInactive(leaseAgreement);
        leaseAgreementRepository.setEndDateNow(leaseAgreement);

        return "redirect:/leaseDetails?lease_agreement_id=" + leaseAgreementId;

    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }




}
