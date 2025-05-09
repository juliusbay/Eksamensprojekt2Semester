package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Damage;
import org.example.eksamensprojekt2semester.Repository.ConditionReportRepository;
import org.example.eksamensprojekt2semester.Repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ConditionReportController {

    @Autowired
    ConditionReportRepository conditionReportRepo;

    @Autowired
    DamageRepository damageRepo;

    @GetMapping ("/create-condition-report")
    public String createConditionReport(@RequestParam("fk_vehicle_id") int vehicleID, Model model){
        List<Damage> damages = damageRepo.getDamageByVehicleID(vehicleID);
        model.addAttribute("vehicle_id", vehicleID);
        model.addAttribute("damages", damages);

        return "create-condition-report";
    }

    @PostMapping ("createConditionReport")
    public String saveCreateConditionReport(@RequestParam("fk_vehicle_id") int vehicleID,
                                            @RequestParam("handled_by") String handledBy,
                                            @RequestParam("report_date")Date reportDate,
                                            @RequestParam("fk_damage_id") int damageId,
                                            Model model,
                                            HttpSession session){





        return "redirect:/create-condition-report?fk_vehicle_id"+vehicleID;
    }
}
