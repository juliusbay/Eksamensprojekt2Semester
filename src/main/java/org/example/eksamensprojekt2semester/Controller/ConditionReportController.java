package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.*;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.example.eksamensprojekt2semester.Repository.ConditionReportRepository;
import org.example.eksamensprojekt2semester.Repository.DamageRepository;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

//Lavet af Julius
@Controller
public class ConditionReportController {

    @Autowired
    ConditionReportRepository conditionReportRepo;

    @Autowired
    DamageRepository damageRepo;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    LeaseAgreementRepository leaseAgreementRepository;

    @Autowired
    CarRepository carRepository;

    // Page for editing a condition report (consisting of damages) based on the condition report's ID
    @GetMapping ("/condition-report")
    public String createConditionReport(@RequestParam("condition_report_id") int conditionReportId, Model model,
                                        HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        List<Damage> damages = damageRepo.getDamageByConditionReportId(conditionReportId);
        LeaseAgreement leaseAgreement = leaseAgreementRepository.getLeaseAgreementByVehicleId(conditionReportRepo.getVehicleIdByConditionReportId(conditionReportId));
        Customer customer = leaseAgreement.getCustomer();
        ConditionReport conditionReport = conditionReportRepo.getConditionReportByReportId(conditionReportId);

        // Below is the calculations for the price matrix included in the condition report
        double priceOfLeaseAgreement = leaseAgreement.leasePrice;
        double totalPriceOfDamages = 0;
        for (Damage d : damages){
            totalPriceOfDamages += d.getDamagePrice();
        }

        double excessKilometers = conditionReport.getExcessKilometers();
        double totalPriceOfExcessKilometers = conditionReport.getExcessKilometers() * 0.75;

        double totalPriceToPay = priceOfLeaseAgreement+totalPriceOfDamages+totalPriceOfExcessKilometers;

        model.addAttribute("condition_report_id", conditionReportId);
        model.addAttribute("damages", damages);
        model.addAttribute("priceOfLeaseAgreement", priceOfLeaseAgreement);
        model.addAttribute("totalPriceOfDamages", totalPriceOfDamages);
        model.addAttribute("totalPriceToPay", totalPriceToPay);
        model.addAttribute("customer", customer);
        model.addAttribute("conditionReport", conditionReport);
        model.addAttribute("excessKilometers", excessKilometers);
        model.addAttribute("totalPriceOfExcessKilometers", totalPriceOfExcessKilometers);

        return "condition-report";
    }

    // Postmapping for creating the condition report itself
    @PostMapping ("createConditionReport")
    public String saveCreateConditionReport(@RequestParam("vehicle_id") int vehicleID,
                                            HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        // Gets timestamp from current moment
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Fetches employee object from logged in session
        Employee employee = (Employee) session.getAttribute("loggedInUser");
        String handledBy = employee.getShortName();

        ConditionReport conditionReport = new ConditionReport(vehicleID, handledBy, currentTimestamp);
        conditionReportRepo.createConditionReport(conditionReport);

        // Fetches the car and updates the car status
        Car car = carRepository.getCarById(vehicleID);
        car.setStatusFromString("GETTING_REPAIRED");
        carRepository.updateCar(car);

        // Fetches the reportId after creation, so the redirection goes to the condition report
        int reportId = conditionReportRepo.getConditionReportByVehicleId(vehicleID).getConditionReportId();

        return "redirect:/condition-report?condition_report_id="+reportId;
    }

    // Postmapping for editing the condition reports excess kilometers and description
    @PostMapping ("editConditionReport")
    public String saveEditConditionReport(@RequestParam("condition_report_id") int reportId,
                                          @RequestParam("excess_kilometers") double excessKilometers,
                                          @RequestParam("report_description") String reportDescription,
                                          HttpSession session){
        if (!employeeController.isUserLoggedIn(session)) {
            return "redirect:/";
        }

        conditionReportRepo.updateExcessKilometersAndDescriptionFromConditionReportId(reportId, excessKilometers, reportDescription);

        return "redirect:/condition-report?condition_report_id="+reportId;
    }


    // Method for completing the condition report and making it un-editable
    @PostMapping("completeConditionReport")
    public String completeConditionReport(@RequestParam("condition_report_id") int reportId,
                                          HttpSession session){

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); //Fetches current date and time

        Employee employee = (Employee) session.getAttribute("loggedInUser");
        String handledBy = employee.getShortName();                             //Gets username from currently logged in user

        boolean completed = true;

        ConditionReport conditionReport = new ConditionReport(reportId, handledBy, currentTimestamp, completed);
        conditionReportRepo.updateConditionReport(conditionReport);

        // Fetches the car and updates the car status
        Car car = carRepository.getCarById(conditionReportRepo.getVehicleIdByConditionReportId(reportId));
        car.setStatusFromString("READY_FOR_TRANSPORT");
        carRepository.updateCar(car);


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
