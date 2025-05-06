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
    public int chosen_optionals;
    public int vehicleId;
    public int customerId;
    public LeaseType leaseType;
    public int leasePrice;
    public Date leaseStartDate;
    public Date leaseEndDate;
    public boolean initialPayment;
    public String returnLocation;


    public int getLeaseAgreementId() {
        return leaseAgreementId;
    }

    public void setLeaseAgreementId(int leaseAgreementId) {
        this.leaseAgreementId = leaseAgreementId;
    }

    public int getChosen_optionals() {
        return chosen_optionals;
    }

    public void setChosen_optionals(int chosen_optionals) {
        this.chosen_optionals = chosen_optionals;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public boolean isInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(boolean initialPayment) {
        this.initialPayment = initialPayment;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

}
