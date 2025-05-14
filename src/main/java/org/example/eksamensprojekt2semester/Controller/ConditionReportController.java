package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.*;
import org.example.eksamensprojekt2semester.Repository.ConditionReportRepository;
import org.example.eksamensprojekt2semester.Repository.DamageRepository;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.example.eksamensprojekt2semester.Service.ConditionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ConditionReportController {

    @Autowired
    ConditionReportRepository conditionReportRepo;

    @Autowired
    DamageRepository damageRepo;

    @Autowired
    ConditionReportService conditionReportService;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    LeaseAgreementRepository leaseAgreementRepository;

    // Page for creating a condition report (consisting of damages) based on the condition report's ID
    @GetMapping ("/condition-report")
    public String createConditionReport(@RequestParam("condition_report_id") int conditionReportId, Model model,
                                        HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        List<Damage> damages = damageRepo.getDamageByConditionReportId(conditionReportId);
        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementByVehicleId(conditionReportRepo.getVehicleIdByConditionReportId(conditionReportId));
        Customer customer = leaseAgreement.getCustomer();

        double priceOfLeaseAgreement = leaseAgreement.leasePrice;
        double totalPriceOfDamages = 0;
        for (Damage d : damages){
            totalPriceOfDamages += d.getDamagePrice();
        }
        double totalPriceToPay = priceOfLeaseAgreement+totalPriceOfDamages;

        model.addAttribute("condition_report_id", conditionReportId);
        model.addAttribute("damages", damages);
        model.addAttribute("priceOfLeaseAgreement", priceOfLeaseAgreement);
        model.addAttribute("totalPriceOfDamages", totalPriceOfDamages);
        model.addAttribute("totalPriceToPay", totalPriceToPay);
        model.addAttribute("customer", customer);

        return "condition-report";
    }

    // Postmapping for creating the condition report itself
    @PostMapping ("createConditionReport")
    public String saveCreateConditionReport(@RequestParam("vehicle_id") int vehicleID,
                                            HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());

        java.util.Date convert = conditionReportService.dateFormatter(today);

        // We need to convert from java.util.date to java.sql.date
        java.sql.Date sqlDate=new java.sql.Date(convert.getTime());

        Employee employee = (Employee) session.getAttribute("loggedInUser");
        String handledBy = employee.getShortName();

        ConditionReport conditionReport = new ConditionReport(vehicleID, handledBy, sqlDate);
        conditionReportRepo.createConditionReport(conditionReport);
        int reportId = conditionReportRepo.getConditionReportByVehicleId(vehicleID).getConditionReportId();

        return "redirect:/condition-report?condition_report_id="+reportId;
    }

    // Postmapping for editing the condition report
    @PostMapping ("editConditionReport")
    public String saveEditConditionReport(@RequestParam("fk_vehicle_id") int vehicleID,
                                          @RequestParam("condition_report_id") int reportId,
                                            @RequestParam("handled_by") String handledBy,
                                          HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

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
    public String deleteConditionReport(@RequestParam("condition_report_id") int reportId,
                                        HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        conditionReportRepo.deleteConditionReportFromId(reportId);
        return "redirect:/";
    }
}
