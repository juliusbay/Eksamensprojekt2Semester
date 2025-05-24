package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.PurchaseAgreement;
import org.example.eksamensprojekt2semester.Repository.PurchaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

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

        // Update an existing purchase agreement
        @PostMapping("/saveUpdatePurchaseAgreement")
        public String updatePurchaseAgreement(@RequestParam("purchase_agreement_id") int purchaseAgreementId,
                                              @RequestParam("fk_vehicle_id") int fkVehicleId,
                                              @RequestParam("fk_customer_id") int fkCustomerId,
                                              @RequestParam("car_price") double carPrice,
                                              @RequestParam("paid") boolean paid,
                                              HttpSession session) {

            if (!isUserLoggedIn(session)) {
                return "redirect:/";
            }

            PurchaseAgreement purchaseAgreement = new PurchaseAgreement(purchaseAgreementId, fkVehicleId, fkCustomerId, paid, carPrice);
            purchaseAgreementRepository.updatePurchaseAgreement(purchaseAgreement);

            return "redirect:/purchase_dashboard";
        }

        // Delete a purchase agreement
        @PostMapping("/deletePurchaseAgreement")
        public String deletePurchaseAgreement(@RequestParam("purchase_agreement_id") int purchaseAgreementId,
                                              HttpSession session) {

            if (!isUserLoggedIn(session)) {
                return "redirect:/";
            }

            purchaseAgreementRepository.deletePurchaseAgreementById(purchaseAgreementId);
            return "redirect:/purchase_dashboard";
        }

        // Get a purchase agreement by ID
        @PostMapping("/purchase_agreement_details")
        public String getPurchaseAgreementById(@RequestParam("purchase_agreement_id") int purchaseAgreementId,
                                               Model model,
                                               HttpSession session) {

            if (!isUserLoggedIn(session)) {
                return "redirect:/";
            }

            PurchaseAgreement purchaseAgreement = purchaseAgreementRepository.getPurchaseAgreementById(purchaseAgreementId);
            model.addAttribute("purchaseAgreement", purchaseAgreement);

            return "redirect:/purchase_agreement_details" + purchaseAgreementId;
        }

        public boolean isUserLoggedIn(HttpSession session) {
            return session.getAttribute("loggedInUser") != null;
        }
    }

