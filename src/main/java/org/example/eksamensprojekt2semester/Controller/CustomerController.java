package org.example.eksamensprojekt2semester.Controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Customer;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.example.eksamensprojekt2semester.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;


    @GetMapping("createCustomerPage")
    public String customers(Model model, HttpSession session) throws SQLException {



        model.addAttribute("cars",carRepository.getAllCars());
        model.addAttribute("customers", customerRepository.getAllCustomers());



        return "create-customer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestParam("first_name")String firstName,
                                 @RequestParam("last_name") String lastName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone_number") int phoneNumber,
                                 @RequestParam("address") String address,
                                 @RequestParam("city") String city,
                                 @RequestParam("postal_code") int postalCode,
                                 @RequestParam("cpr_number") int cprNumber,
                                 @RequestParam("fk_vehicle_id") int fkVehicleId,
                                 HttpSession session) {
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, address, city, postalCode, cprNumber, fkVehicleId);

        customerRepository.createCustomer(customer);

        return "redirect:/";
    }
}
