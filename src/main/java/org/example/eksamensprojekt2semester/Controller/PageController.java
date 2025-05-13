package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.*;
import org.example.eksamensprojekt2semester.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    ChoiceRepository choiceRepository;


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
        Map<Integer, ConditionReport> conditionReportsMap = conditionReportRepository.getAllConditionReports();

        model.addAttribute("cars", cars);
        model.addAttribute("leaseAgreements", leaseAgreements);
        model.addAttribute("carModels", carModels);
        model.addAttribute("conditionReportsMap", conditionReportsMap);
        model.addAttribute("conditionReports", new ArrayList<>(conditionReportsMap.values()));
        model.addAttribute("damages", damages);
        model.addAttribute("employees", employees);
        model.addAttribute("purchaseAgreements", purchaseAgreements);
        model.addAttribute("customers", customers);


        return "carsTestSide";
    }

    // Method that counts all cars and shows the numbers for how many are rented out and how many are available
    @GetMapping("/statistics")
    public String getStatistics(Model model, HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/login";
        }

        int numberOfCars = carRepository.getAllCars().size();
        int numberOfCarsRentedOut = carRepository.getCarsByRentedOutStatus(true);
        int numberOfCarsAvailable = numberOfCars - numberOfCarsRentedOut;

        model.addAttribute("numberOfCars", numberOfCars);
        model.addAttribute("numberOfCarsRentedOut", numberOfCarsRentedOut);
        model.addAttribute("numberOfCarsAvailable", numberOfCarsAvailable);


        return "statistics";
    }
}
