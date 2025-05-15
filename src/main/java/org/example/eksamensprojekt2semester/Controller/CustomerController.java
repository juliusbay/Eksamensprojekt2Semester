package org.example.eksamensprojekt2semester.Controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    @Autowired
    CustomerRepository CustomerRepository;



    @PostMapping
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
        s




        return "createCustomer";
    }
}
