package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.Status;

public class Car {

    private int vehicleId;
    private CarModel carModel;
    private String vinNumber;
    private String color;
    // private double monthlyPrice;
    private boolean bought;
    private Status status;
    private boolean rentedOut;

    public Car(int vehicleId, CarModel carModel, String vinNumber, String color, boolean bought, Status status) {
        this.vehicleId = vehicleId;
        this.carModel = carModel;
        this.vinNumber = vinNumber;
        this.color = color;
        // this.monthlyPrice = monthlyPrice;
        this.bought = bought;

    }

    public Car(CarModel carModel, String vinNumber, String color) {
        this.carModel = carModel;
        this.vinNumber = vinNumber;
        this.color = color;
        status = Status.READY;

    }
    public Car(CarModel carModel, String vinNumber, String color,  boolean bought) {
        this.carModel = carModel;
        this.vinNumber = vinNumber;
        this.color = color;
        this.bought = bought;
        status = Status.READY;
    }

    public Car() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public double getMonthlyPrice() {
//        return monthlyPrice;
//    }
//
//    public void setMonthlyPrice(double monthlyPrice) {
//        this.monthlyPrice = monthlyPrice;
//    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusValue() {
        return status.name();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatusFromString(String status) {
        this.status = Status.valueOf(status);
    }

    public boolean isRentedOut() {
        return rentedOut;
    }

    public void setRentedOut(boolean rentedOut) {
        this.rentedOut = rentedOut;
    }
}
