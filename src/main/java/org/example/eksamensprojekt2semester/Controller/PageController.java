package org.example.eksamensprojekt2semester.Controller;

import org.example.eksamensprojekt2semester.Model.*;
import org.example.eksamensprojekt2semester.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    ChoiceRepository choiceRepository;


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/cars")
    public String getEveryTable(Model model) throws SQLException {
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
}
