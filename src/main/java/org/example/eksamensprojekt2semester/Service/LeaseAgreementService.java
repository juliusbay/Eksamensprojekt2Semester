package org.example.eksamensprojekt2semester.Service;

import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

@Service
public class LeaseAgreementService {


    public void noNegativePriceLease(LeaseAgreement leaseAgreement) {
       if(leaseAgreement.getLeasePrice() <=0){
           throw new IllegalArgumentException("LeasePrice must be greater than 0");

       }

    }

    public void isEndDateBeforeStartDate(LeaseAgreement leaseAgreement) {
        if(leaseAgreement.getLeaseEndDate().before(leaseAgreement.getLeaseStartDate())){
            throw new IllegalArgumentException("LeaseEndDate must be before startDate");
        }

    }

    public void minimum120daysAgreement(LeaseAgreement leaseAgreement) {
        if (leaseAgreement.leaseType == LeaseAgreement.LeaseType.UNLIMITED) {
            LocalDate startDate = leaseAgreement.leaseStartDate.toLocalDateTime().toLocalDate();
            LocalDate endDate = leaseAgreement.leaseEndDate.toLocalDateTime().toLocalDate();

            //A standard set of date periods units. Converts the DAYS to numbers. can also use CENTURIES, DECADES ERAS etc.
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            //daysbetwwen is now a number, so we can check if it is less than 120, and then if it is viable
            if (daysBetween < 120) {
                throw new IllegalArgumentException("Unlimited lease agreements must be at least 120 days long.");
            }
        }

    }

}
