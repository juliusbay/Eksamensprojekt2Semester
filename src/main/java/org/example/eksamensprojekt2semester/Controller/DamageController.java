package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Damage;
import org.example.eksamensprojekt2semester.Repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
@Controller
public class DamageController {

    @Autowired
    DamageRepository damageRepository;

    // Create a new damage report
    @PostMapping("/createDamage")
    public String createDamage(@RequestParam("fk_vehicle_id") int fkVehicleId,
                               @RequestParam("damage_type") String damageType,
                               @RequestParam("damage_date") Date damageDate,
                               @RequestParam("damage_price") double damagePrice,
                               HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        Damage damage = new Damage(fkVehicleId, damageType, damagePrice, damageDate);
        damageRepository.createDamage(damage);
        return "redirect:/damage_dashboard";
    }

    // Update an existing damage report
    @PostMapping("/saveUpdateDamage")
    public String updateDamage(@RequestParam("damage_id") int damageId,
                               @RequestParam("fk_vehicle_id") int fkVehicleId,
                               @RequestParam("damage_type") String damageType,
                               @RequestParam("damage_date") Date damageDate,
                               @RequestParam("damage_price") double damagePrice,
                               HttpSession session) throws SQLException {
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }
        Damage damage = new Damage(damageId, fkVehicleId, damageType, damagePrice, damageDate);
        damageRepository.updateDamage(damage);

        return "redirect:/damage_dashboard";
    }

    // Delete a damage report
    @PostMapping("/deleteDamage")
    public String deleteDamage(@RequestParam("damage_id") int damageId,
                               HttpSession session) {

        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        damageRepository.deleteDamageByID(damageId);
        return "redirect:/damage_dashboard";
    }

    // Get damage by ID
    @PostMapping("/damage_details")
    public String getDamageById(@RequestParam("damage_id") int damageId,
                                Model model,
                                HttpSession session) {

        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        Damage damage = damageRepository.getDamageByID(damageId);
        model.addAttribute("damage", damage);

        return "redirect:/damage_details" + damageId;
    }

    // Get all damage reports by vehicle ID
    @GetMapping("/damage_dashboard")
    public String getDamageByVehicleId(@RequestParam("vehicle_id") int vehicleId,
                                       Model model) {

        ArrayList<Damage> damages = damageRepository.getDamageByVehicleID(vehicleId);
        model.addAttribute("damages", damages);

        return "damage_dashboard";
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }
}

