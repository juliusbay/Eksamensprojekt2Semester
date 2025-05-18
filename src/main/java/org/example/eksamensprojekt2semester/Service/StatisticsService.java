package org.example.eksamensprojekt2semester.Service;

import org.example.eksamensprojekt2semester.Model.Car;
import org.example.eksamensprojekt2semester.Model.ConditionReport;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.example.eksamensprojekt2semester.Repository.ConditionReportRepository;
import org.example.eksamensprojekt2semester.Repository.LeaseAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    LeaseAgreementRepository leaseAgreementRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ConditionReportRepository conditionReportRepository;

    // Formats the Duration value for below calculations that return Duration
    public String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        if (days > 0) {
            return String.format("%d dage, %d timer, %d minutter", days, hours, minutes);
        } else {
            return String.format("%d timer, %d minutter", hours, minutes);
        }
    }

    public double calculateTotalLeaseAgreementValue() {
        ArrayList<LeaseAgreement> leaseAgreements = leaseAgreementRepository.getLeaseAgreementsByActiveStatus(true);

        if (leaseAgreements.isEmpty()) {
            return 0;
        }

        double totalValueOfActiveLeaseAgreements = 0;
        for (LeaseAgreement leases : leaseAgreements) {
            totalValueOfActiveLeaseAgreements += leases.leasePrice;
        }

        return totalValueOfActiveLeaseAgreements;
    }

    public double calculateAverageLeaseAgreementValue() {
        ArrayList<LeaseAgreement> leaseAgreements = leaseAgreementRepository.getLeaseAgreementsByActiveStatus(true);

        if (leaseAgreements.isEmpty()) {
            return 0;
        }

        double totalValueOfActiveLeaseAgreements = 0;
        for (LeaseAgreement leases : leaseAgreements) {
            totalValueOfActiveLeaseAgreements += leases.leasePrice;
        }

        return totalValueOfActiveLeaseAgreements / leaseAgreements.size();
    }

    public Duration calculateAverageLeasePeriod() {
        List<LeaseAgreement> leaseAgreements = leaseAgreementRepository.getAllLeaseAgreements();

        if (leaseAgreements.isEmpty()) {
            return Duration.ZERO;
        }

        long totalDays = 0;
        int leases = 0;

        for (LeaseAgreement lease : leaseAgreements) {
            LocalDate start = lease.getLeaseStartDate().toLocalDateTime().toLocalDate();
            LocalDate end = lease.getLeaseEndDate().toLocalDateTime().toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            totalDays += daysBetween;
            leases++;
        }

        long averageDays = totalDays / leases;
        return Duration.ofDays(averageDays);
    }

    public Duration calculateAverageGarageWaitingTime(List<LeaseAgreement> leaseAgreements,
                                               Map<Integer, ConditionReport> conditionReportsMap){

        if (conditionReportsMap.isEmpty()) {
            return Duration.ZERO;
        }

        long totalSeconds = 0;
        int index = 0;

        for (LeaseAgreement lease : leaseAgreements) {
            int vehicleId = lease.getCar().getVehicleId();
            ConditionReport report = conditionReportsMap.get(vehicleId); // Fetches the condition report for corresponding vehicle id from lease agreement

            LocalDateTime leaseEnd = lease.getLeaseEndDate().toLocalDateTime();
            LocalDateTime reportStart = report.getReportStartDate().toLocalDateTime();

            long secondsBetween = Duration.between(leaseEnd, reportStart).getSeconds();
            totalSeconds += secondsBetween;
            index++;

        }

        long avgSeconds = totalSeconds / index;
        return Duration.ofSeconds(avgSeconds);
    }

    public Duration averageGarageInspectionTime(){
        List<ConditionReport> conditionReports = conditionReportRepository.getConditionReportsByCompletionStatus(true);

        if (conditionReports.isEmpty()) {
            System.out.println("Ingen rapporter");
            return Duration.ZERO;
        }

        long totalSeconds = 0;
        int reports = 0;

        for (ConditionReport report : conditionReports) {
            if (report.getReportStartDate() == null || report.getReportCompletedDate() == null){
                System.out.println("Datoerne er null");
                continue;
            }

            LocalDateTime start = report.getReportStartDate().toLocalDateTime();
            LocalDateTime end = report.getReportCompletedDate().toLocalDateTime();

            long seconds = Duration.between(start, end).getSeconds();
            totalSeconds += seconds;
            reports++;
        }

        long averageSeconds = totalSeconds / reports;
        return Duration.ofSeconds(averageSeconds);
    }

    public Duration calculateAverageTimeToLease(
            List<Car> cars,
            List<LeaseAgreement> leaseAgreements) {

        if (cars.isEmpty() || leaseAgreements.isEmpty()) {
            return Duration.ZERO;
        }

        long totalSeconds = 0;
        int count = 0;

        for (Car car : cars) {
            int vehicleId = car.getVehicleId();


            if (car.getReceivedDate() == null) {
                continue;
            }

            LocalDateTime carReceivedDate = car.getReceivedDate().toLocalDateTime();
            LocalDateTime leaseStartDate = null;

            for (LeaseAgreement lease : leaseAgreements) {
                if (lease.getCar() != null && lease.getCar().getVehicleId() == vehicleId && lease.getLeaseStartDate() != null
                ) {
                    leaseStartDate = lease.getLeaseStartDate().toLocalDateTime();
                }

                // If the car has not been leased, it will take the time from received date until now
                if (leaseStartDate == null) {
                    leaseStartDate = LocalDateTime.now();
                }
            }

            long secondsBetween = Duration.between(carReceivedDate, leaseStartDate).getSeconds();
            totalSeconds += secondsBetween;
            count++;

        }

        long avgSeconds = totalSeconds / count;
        return Duration.ofSeconds(avgSeconds);
    }
}
