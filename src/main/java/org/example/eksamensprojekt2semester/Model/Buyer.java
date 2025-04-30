package org.example.eksamensprojekt2semester.Model;

public class Buyer {
    int buyerID;
    String fullName;
    String email;
    int phoneNumber;
    boolean isPreBought;
    int vehicleID;

    public Buyer(int buyerID, String fullName, String email, int phoneNumber, boolean isPreBought, int vehicleID) {
        this.buyerID = buyerID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isPreBought = isPreBought;
        this.vehicleID = vehicleID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPreBought() {
        return isPreBought;
    }

    public void setPreBought(boolean preBought) {
        isPreBought = preBought;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
}
