package org.example.eksamensprojekt2semester.Controller;


import org.example.eksamensprojekt2semester.Model.Customer;
import org.example.eksamensprojekt2semester.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;


    @PostMapping("/createCustomer")
    public String createCustomer(@RequestParam("first_name")String firstName,
                                 @RequestParam("last_name") String lastName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone_number") int phoneNumber,
                                 @RequestParam("address") String address,
                                 @RequestParam("city") String city,
                                 @RequestParam("postal_code") int postalCode,
                                 @RequestParam("cpr_number") String cprNumber) {
        Customer customer = new Customer(firstName, lastName, email, phoneNumber, address, city, postalCode, cprNumber);

        //Regular expression ie. regex. says first part must contain 6 (d) digits followed by - and then 4 digits
        if (!cprNumber.matches("\\d{6}-\\d{4}")) {
            System.out.println("Invalid CPR number");
            throw new IllegalArgumentException("Invalid CPR number");
        }
        customerRepository.createCustomer(customer);
        return "redirect:/";
    }
}
