package org.example.eksamensprojekt2semester.Service;

import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

        LocalDate date1 = LocalDate.of(2020, 1, 1);
        LocalDate date2 = LocalDate.of(2020, 12, 31);

        long daysbetween = ChronoUnit.DAYS.between(leaseAgreement.leaseStartDate,leaseAgreement.leaseEndDate)

    }

}
