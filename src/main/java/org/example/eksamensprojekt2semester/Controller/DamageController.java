package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Damage;
import org.example.eksamensprojekt2semester.Repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.SQLException;

@Controller
public class DamageController {

    @Autowired
    DamageRepository damageRepository;

    // Create a new damage
    @PostMapping("/createDamage")
    public String createDamage(@RequestParam("condition_report_id") int conditionReportId,
                               @RequestParam("damage_type") String damageType,
                               @RequestParam("damage_price") double damagePrice,
                               HttpSession session) {
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        Damage damage = new Damage(conditionReportId, damageType, damagePrice);
        damageRepository.createDamage(damage);
        return "redirect:/condition-report?condition_report_id="+conditionReportId;
    }

    // Update an existing damage
    @PostMapping("/saveUpdateDamage")
    public String updateDamage(@RequestParam("damage_id") int damageId,
                               @RequestParam("condition_report_id") int conditionReportId,
                               @RequestParam("damage_type") String damageType,
                               @RequestParam("damage_price") double damagePrice,
                               HttpSession session) throws SQLException {
        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }
        Damage damage = new Damage(damageId, conditionReportId, damageType, damagePrice);
        damageRepository.updateDamage(damage);

        return "redirect:/condition-report?condition_report_id="+conditionReportId;
    }

    // Delete a damage
    @PostMapping("/deleteDamage")
    public String deleteDamage(@RequestParam("damage_id") int damageId,
                               @RequestParam("condition_report_id") int conditionReportId,
                               HttpSession session) {

        if (!isUserLoggedIn(session)) {
            return "redirect:/";
        }

        damageRepository.deleteDamageByID(damageId);
        return "redirect:/condition-report?condition_report_id="+conditionReportId;
    }


    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }
}

