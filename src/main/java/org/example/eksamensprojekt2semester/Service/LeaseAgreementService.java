package org.example.eksamensprojekt2semester.Service;

import org.example.eksamensprojekt2semester.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
