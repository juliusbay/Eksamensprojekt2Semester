package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.LeaseType;

import java.sql.Timestamp;

public class LeaseAgreement {
    public int leaseAgreementId;
    public int fkVehicleId;
    public int fkCustomerId;
    public LeaseType leaseType;
    public double leasePrice;
    public Timestamp leaseStartDate;
    public Timestamp leaseEndDate;
    public String returnLocation;
    private Customer customer;
    private boolean active;
    private Car car;

    public LeaseAgreement(int fkVehicleId, int fkCustomerId, LeaseType leaseType, double leasePrice, Timestamp leaseStartDate, Timestamp leaseEndDate, String returnLocation) {
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

    public int getFkCustomerId() {
        return fkCustomerId;
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

    public double getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(double leasePrice) {
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

    public void setLeaseTypeFromString(String leaseType) {
        this.leaseType = LeaseType.valueOf(leaseType);
    }

}
