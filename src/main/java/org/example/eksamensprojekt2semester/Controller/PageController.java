package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.*;
import org.example.eksamensprojekt2semester.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    LeaseAgreementRepository leaseAgreementRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarModelRepository carModelRepository;

    @Autowired
    ConditionReportRepository conditionReportRepository;

    @Autowired
    DamageRepository damageRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PurchaseAgreementRepository purchaseAgreementRepository;

    @Autowired
    EmployeeController employeeController;


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/login";
    }


    @GetMapping("/cars")
    public String getEveryTable(Model model, HttpSession session) throws SQLException {
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/login";
        }

        ArrayList<LeaseAgreement> leaseAgreements = leaseAgreementRepository.getAllLeaseAgreements();
        ArrayList<Car> cars = carRepository.getAllCars();
        ArrayList<CarModel> carModels = carModelRepository.getAllCarModels();
        ArrayList<Damage> damages = damageRepository.getAllDamages();
        ArrayList<Employee> employees = employeeRepository.getAllEmployees();
        ArrayList<PurchaseAgreement> purchaseAgreements = purchaseAgreementRepository.getAllPurchaseAgreements();
        ArrayList<Customer> customers = customerRepository.getAllCustomers();
        System.out.println(customers);
        Map<Integer, ConditionReport> conditionReportsMap = conditionReportRepository.getAllConditionReports();

        for (LeaseAgreement leaseAgreement : leaseAgreements) {
            leaseAgreementRepository.checkLeaseEndDate(leaseAgreement);
        }


        model.addAttribute("cars", cars);
        model.addAttribute("leaseAgreements", leaseAgreements);
        model.addAttribute("carModels", carModels);
        model.addAttribute("conditionReportsMap", conditionReportsMap);
        model.addAttribute("conditionReports", new ArrayList<>(conditionReportsMap.values()));
        model.addAttribute("damages", damages);
        model.addAttribute("employees", employees);
        model.addAttribute("purchaseAgreements", purchaseAgreements);
        model.addAttribute("customers", customers);


        return "dashboard";
    }

    // Method that counts all cars and shows the numbers for how many are rented out and how many are available
    // Also counts the value of lease agreements and calculates average value of lease agreements
    @GetMapping("/statistics")
    public String getStatistics(Model model, HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/login";
        }

        ArrayList<LeaseAgreement> leaseAgreements = leaseAgreementRepository.getLeaseAgreementsByActiveStatus(true);
        double totalValueOfActiveLeaseAgreements = 0;
        for (LeaseAgreement leases : leaseAgreements){
            totalValueOfActiveLeaseAgreements += leases.leasePrice;
        }
        double averageLeaseAgreementValue = totalValueOfActiveLeaseAgreements/leaseAgreements.size();

        model.addAttribute("totalValueOfActiveLeaseAgreements", totalValueOfActiveLeaseAgreements);
        model.addAttribute("averageLeaseAgreementValue", averageLeaseAgreementValue);

        return "statistics";
    }

    // Tilføjer den til alle getMappings så den kan bruges i navbaren
    @ModelAttribute
    public void showCarAvailability(Model model){
        int numberOfCars = carRepository.getAllCars().size();
        int numberOfCarsRentedOut = carRepository.getCarsByRentedOutStatus(true);
        int numberOfCarsAvailable = numberOfCars - numberOfCarsRentedOut;
        int carThreshold = 10;

        model.addAttribute("numberOfCars", numberOfCars);
        model.addAttribute("numberOfCarsRentedOut", numberOfCarsRentedOut);
        model.addAttribute("numberOfCarsAvailable", numberOfCarsAvailable);
        model.addAttribute("carThreshold", carThreshold);
    }
}
