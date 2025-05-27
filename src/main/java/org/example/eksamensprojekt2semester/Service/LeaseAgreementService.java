package org.example.eksamensprojekt2semester.Service;

import org.example.eksamensprojekt2semester.Enum.LeaseType;
import org.example.eksamensprojekt2semester.Model.LeaseAgreement;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Lavet af Frederik
@Service
public class LeaseAgreementService {


    public void noNegativePriceLease(LeaseAgreement leaseAgreement) {

       if(leaseAgreement.getLeasePrice() <=0){
           throw new IllegalArgumentException("Prisen på leasen må ikke være under 0");

       }

    }

    public void isEndDateBeforeStartDate(LeaseAgreement leaseAgreement) {
        if(leaseAgreement.getLeaseEndDate().before(leaseAgreement.getLeaseStartDate())){
            throw new IllegalArgumentException("Slutdagen på lejeaftalen må ikke være før startdatoen");
        }

    }

    public void minimum120daysAgreement(LeaseAgreement leaseAgreement) {

        if (leaseAgreement.leaseType == LeaseType.UNLIMITED) {
            LocalDate startDate = leaseAgreement.leaseStartDate.toLocalDateTime().toLocalDate();
            LocalDate endDate = leaseAgreement.leaseEndDate.toLocalDateTime().toLocalDate();

            //A standard set of date periods units. Converts the DAYS to numbers. can also use CENTURIES, DECADES ERAS etc.
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            //daysbetwwen is now a number, so we can check if it is less than 120, and then if it is viable
            if (daysBetween < 120) {
                throw new IllegalArgumentException("Unlimited lejeaftaler skal minimum være 120 dage lange");
            }
        }

    }

    public void maximum120daysAgreement(LeaseAgreement leaseAgreement) {
        if (leaseAgreement.leaseType == LeaseType.LIMITED) {
            LocalDate startDate = leaseAgreement.leaseStartDate.toLocalDateTime().toLocalDate();
            LocalDate endDate = leaseAgreement.leaseEndDate.toLocalDateTime().toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            if (daysBetween > 120) {
                throw new IllegalArgumentException("Limited leje aftaler må ikke være 120 dage lange");

            }
        }
    }

}
