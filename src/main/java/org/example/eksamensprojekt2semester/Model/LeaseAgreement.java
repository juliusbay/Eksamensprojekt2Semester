package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class LeaseAgreement {
    public enum LeaseType {
        LIMITED("Limited"),
        UNLIMITED("Unlimited");

        private final String dbValue;

        LeaseType(String dbValue) {
            this.dbValue = dbValue;
        }

        @Override
        public String toString() {
            return dbValue;
        }

        public static LeaseType fromString(String value) {
            for (LeaseType type : LeaseType.values()) {
                if (type.dbValue.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown lease type: " + value);
        }
    }

    public int leaseAgreementId;
    public int fkChosenChoice;
    public int fkVehicleId;
    public int fkCustomerId;
    public LeaseType leaseType;
    public int leasePrice;
    public Date leaseStartDate;
    public Date leaseEndDate;
    public String returnLocation;


    public int getLeaseAgreementId() {
        return leaseAgreementId;
    }

    public void setLeaseAgreementId(int leaseAgreementId) {
        this.leaseAgreementId = leaseAgreementId;
    }

    public int getFkChosenChoice() {
        return fkChosenChoice;
    }

    public void setFkChosenChoice(int fkChosenChoice) {
        this.fkChosenChoice = fkChosenChoice;
    }

    public int getFkVehicleId() {
        return fkVehicleId;
    }

    public void setFkVehicleId(int fkVehicleId) {
        this.fkVehicleId = fkVehicleId;
    }

    public int getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(int fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }


    public LeaseType getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(LeaseType leaseType) {
        this.leaseType = leaseType;
    }

    public int getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(int leasePrice) {
        this.leasePrice = leasePrice;
    }


    public Date getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

}
