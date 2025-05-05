package org.example.eksamensprojekt2semester.Model;

import java.util.Date;

public class Booking {

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
            for (LeaseType lt : LeaseType.values()) {
                if (lt.dbValue.equalsIgnoreCase(value)) {
                    return lt;
                }
            }
            throw new IllegalArgumentException("Unknown lease type: " + value);
        }
    }
    private int bookingId;
    private int vehicleId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Date leaseStartDate;
    private Date leaseEndDate;
    private double contractPrice;
    private boolean advanceBuyer;
    private LeaseType leaseType;



    public Booking(int bookingId, int vehicleId, String customerName, String customerEmail, String customerPhone, LeaseType leaseType, Date leaseStartDate, Date leaseEndDate, double contractPrice, boolean advanceBuyer) {
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.leaseType = leaseType;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.contractPrice = contractPrice;
        this.advanceBuyer = advanceBuyer;
    }


    public Booking(int vehicleId, String customerName, String customerEmail, String customerPhone, Date leaseStartDate, Date leaseEndDate, double contractPrice, boolean advanceBuyer) {
        this.vehicleId = vehicleId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.contractPrice = contractPrice;
        this.advanceBuyer = advanceBuyer;
    }

    public Booking(int vehicleId, String costumer_name, String customerEmail, String customerPhone, LeaseType leaseType, java.sql.Date sqlDateStart, java.sql.Date sqlDateEnd, int contractPrice, boolean isAdvanceBuyer) {
        this.vehicleId = vehicleId;
        this.customerName =costumer_name;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.leaseStartDate = sqlDateStart;
        this.leaseEndDate = sqlDateEnd;
        this.contractPrice = contractPrice;
        this.advanceBuyer = isAdvanceBuyer;
        this.leaseType =leaseType;
    }

    public LeaseType getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(LeaseType leaseType) {
        this.leaseType = leaseType;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public java.sql.Date getLeaseStartDate() {
        return (java.sql.Date) leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public java.sql.Date getLeaseEndDate() {
        return (java.sql.Date) leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public boolean isAdvanceBuyer() {
        return advanceBuyer;
    }

    public void setAdvanceBuyer(boolean advanceBuyer) {
        this.advanceBuyer = advanceBuyer;
    }
}
