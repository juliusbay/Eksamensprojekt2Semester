package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.Status;

public class Car {

    private int vehicleId;
    private int fkCarModelId;
    private String vinNumber;
    private String color;
    private double monthlyPrice;
    private boolean bought;
    private Status status;

    public Car(int vehicleId, int fkCarModelId, String vinNumber, String color, double monthlyPrice, boolean bought, Status status) {
        this.vehicleId = vehicleId;
        this.fkCarModelId = fkCarModelId;
        this.vinNumber = vinNumber;
        this.color = color;
        this.monthlyPrice = monthlyPrice;
        this.bought = bought;
        this.status = status;
    }

    public Car(int fkCarModelId, String vinNumber, String color, double monthlyPrice) {
        this.fkCarModelId = fkCarModelId;
        this.vinNumber = vinNumber;
        this.color = color;
        this.monthlyPrice = monthlyPrice;
    }
    public Car(int fkCarModelId, String vinNumber, String color, double monthlyPrice,  boolean bought) {
        this.fkCarModelId = fkCarModelId;
        this.vinNumber = vinNumber;
        this.color = color;
        this.monthlyPrice = monthlyPrice;
        this.bought = bought;
    }

    public Car() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getFkCarModelId() {
        return fkCarModelId;
    }

    public void setFkCarModelId(int fkCarModelId) {
        this.fkCarModelId = fkCarModelId;
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

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

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
        this.status = Status.fromString(status);
    }
}
