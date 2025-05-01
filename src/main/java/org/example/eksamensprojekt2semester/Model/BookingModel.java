package org.example.eksamensprojekt2semester.Model;

import java.util.Date;

public class BookingModel {
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
    private int booking_id;
    private int vehicle_id;
    private String customer_name;
    private String customer_email;
    private String customer_phone;
    private Date lease_start_date;
    private Date lease_end_date;
    private double contract_price;
    private boolean advance_buyer;
    private LeaseType lease_type;



    public BookingModel(int booking_id, int vehicle_id, String customer_name, String customer_email, String customer_phone, LeaseType lease_type, Date lease_start_date, Date lease_end_date, double contract_price, boolean advance_buyer) {
        this.booking_id = booking_id;
        this.vehicle_id = vehicle_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.lease_type = lease_type;
        this.lease_start_date = lease_start_date;
        this.lease_end_date = lease_end_date;
        this.contract_price = contract_price;
        this.advance_buyer = advance_buyer;
    }


    public BookingModel(int vehicle_id, String customer_name, String customer_email, String customer_phone, Date lease_start_date, Date lease_end_date, double contract_price, boolean advance_buyer) {
        this.vehicle_id = vehicle_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_phone = customer_phone;
        this.lease_start_date = lease_start_date;
        this.lease_end_date = lease_end_date;
        this.contract_price = contract_price;
        this.advance_buyer = advance_buyer;
    }

    public LeaseType getLease_type() {
        return lease_type;
    }

    public void setLease_type(LeaseType lease_type) {
        this.lease_type = lease_type;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public java.sql.Date getLease_start_date() {
        return lease_start_date;
    }

    public void setLease_start_date(Date lease_start_date) {
        this.lease_start_date = lease_start_date;
    }

    public java.sql.Date getLease_end_date() {
        return lease_end_date;
    }

    public void setLease_end_date(Date lease_end_date) {
        this.lease_end_date = lease_end_date;
    }

    public double getContract_price() {
        return contract_price;
    }

    public void setContract_price(double contract_price) {
        this.contract_price = contract_price;
    }

    public boolean isAdvance_buyer() {
        return advance_buyer;
    }

    public void setAdvance_buyer(boolean advance_buyer) {
        this.advance_buyer = advance_buyer;
    }
}
