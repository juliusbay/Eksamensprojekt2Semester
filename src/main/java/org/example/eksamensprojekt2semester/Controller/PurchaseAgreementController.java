package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.PurchaseAgreement;
import org.example.eksamensprojekt2semester.Repository.PurchaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Lavet af Frederik
@Controller
public class PurchaseAgreementController {

        @Autowired
        PurchaseAgreementRepository purchaseAgreementRepository;

        // Create a new purchase agreement
        @PostMapping("/createPurchaseAgreement")
        public String createPurchaseAgreement(@RequestParam("fk_vehicle_id") int fkVehicleId,
                                              @RequestParam("fk_customer_id") int fkCustomerId,
                                              @RequestParam("car_price") double carPrice,
                                              @RequestParam("paid") boolean paid,
                                              HttpSession session){

            if (!isUserLoggedIn(session)) {
                return "redirect:/";
            }

            PurchaseAgreement purchaseAgreement = new PurchaseAgreement(fkVehicleId, fkCustomerId, paid, carPrice);
            purchaseAgreementRepository.createPurchaseAgreement(purchaseAgreement);

            return "redirect:/dashboard";
        }


        // Delete a purchase agreement
        @PostMapping("/deletePurchaseAgreement")
        public String deletePurchaseAgreement(@RequestParam("purchase_agreement_id") int purchaseAgreementId,
                                              HttpSession session) {

            if (!isUserLoggedIn(session)) {
                return "redirect:/";
            }

            purchaseAgreementRepository.deletePurchaseAgreementById(purchaseAgreementId);
            return "redirect:/dashboard";
        }

        public boolean isUserLoggedIn(HttpSession session) {
            return session.getAttribute("loggedInUser") != null;
        }
    }

