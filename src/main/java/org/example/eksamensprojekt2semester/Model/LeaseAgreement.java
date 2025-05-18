package org.example.eksamensprojekt2semester.Model;

import java.rmi.dgc.Lease;
import java.sql.Date;
import java.sql.Timestamp;

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
    public int fkVehicleId;
    public int fkCustomerId;
    public LeaseType leaseType;
    public int leasePrice;
    public Timestamp leaseStartDate;
    public Timestamp leaseEndDate;
    public String returnLocation;
    private Customer customer;
    private boolean active;
    private Car car;

    public LeaseAgreement(int fkVehicleId, int fkCustomerId, LeaseType leaseType, int leasePrice, Timestamp leaseStartDate, Timestamp leaseEndDate, String returnLocation) {
    this.fkVehicleId = fkVehicleId;
    this.fkCustomerId = fkCustomerId;
    this.leaseType = leaseType;
    this.leasePrice = leasePrice;
    this.leaseStartDate =leaseStartDate;
    this.leaseEndDate =leaseEndDate;
    this.returnLocation = returnLocation;
    }

    public LeaseAgreement() {

    }
    public LeaseAgreement(int leaseAgreement, int fkVehicleId, int fkCustomerId) {
        this.leaseAgreementId = leaseAgreement;
        this.fkVehicleId = fkVehicleId;
        this.fkCustomerId = fkCustomerId;
    }

    public int getLeaseAgreementId() {
        return leaseAgreementId;
    }

    public void setLeaseAgreementId(int leaseAgreementId) {
        this.leaseAgreementId = leaseAgreementId;
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
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
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


    public Timestamp getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Timestamp leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Timestamp getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Timestamp leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "LeaseAgreement{" +
                "leaseAgreementId=" + leaseAgreementId +
                ", fkVehicleId=" + fkVehicleId +
                ", fkCustomerId=" + fkCustomerId +
                ", leaseType=" + leaseType +
                ", leasePrice=" + leasePrice +
                ", leaseStartDate=" + leaseStartDate +
                ", leaseEndDate=" + leaseEndDate +
                ", returnLocation='" + returnLocation + '\'' +
                ", customer=" + customer +
                ", active=" + active +
                '}';
    }
}
