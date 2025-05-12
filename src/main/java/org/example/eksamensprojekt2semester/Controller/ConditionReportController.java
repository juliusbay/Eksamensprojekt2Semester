package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.ConditionReport;
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

    @Autowired
    ConditionReportService conditionReportService;

    // Page for creating a condition report (consisting of damages) based on the vehicle's ID
    @GetMapping ("/create-condition-report")
    public String createConditionReport(@RequestParam("vehicle_id") int vehicleID, Model model){
        List<Damage> damages = damageRepo.getDamageByVehicleID(vehicleID);
        model.addAttribute("vehicle_id", vehicleID);
        model.addAttribute("damages", damages);

        return "create-condition-report";
    }

    // Postmapping for creating the condition report itself
    @PostMapping ("createConditionReport")
    public String saveCreateConditionReport(@RequestParam("fk_vehicle_id") int vehicleID,
                                            @RequestParam("handled_by") String handledBy){
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

        java.util.Date convert = conditionReportService.dateFormatter(today);

        // We need to convert from java.util.date to java.sql.date
        java.sql.Date sqlDate=new java.sql.Date(convert.getTime());

        ConditionReport conditionReport = new ConditionReport(vehicleID, handledBy, sqlDate);
        conditionReportRepo.createConditionReport(conditionReport);
        return "redirect:/";
    }

    // Postmapping for editing the condition report
    @PostMapping ("editConditionReport")
    public String saveEditConditionReport(@RequestParam("fk_vehicle_id") int vehicleID,
                                          @RequestParam("condition_report_id") int reportId,
                                            @RequestParam("handled_by") String handledBy){
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

        java.util.Date convert = conditionReportService.dateFormatter(today);

        // We need to convert from java.util.date to java.sql.date
        java.sql.Date sqlDate=new java.sql.Date(convert.getTime());

        ConditionReport conditionReport = new ConditionReport(reportId, vehicleID, handledBy, sqlDate);
        conditionReportRepo.updateConditionReport(conditionReport);
        return "redirect:/";
    }

    // Postmapping to delete condition reports
    @PostMapping ("deleteConditionReport")
    public String deleteConditionReport(@RequestParam("condition_report_id") int reportId){

        conditionReportRepo.deleteConditionReportFromId(reportId);
        return "redirect:/";
    }
}
