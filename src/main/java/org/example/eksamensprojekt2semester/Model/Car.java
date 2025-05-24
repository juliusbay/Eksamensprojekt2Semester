package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.Status;

import java.sql.Timestamp;

public class Car {

    private int vehicleId;
    private CarModel carModel;
    private String vinNumber;
    private String color;
    private boolean bought;
    private Status status;
    private Timestamp receivedDate;

    public Car(int vehicleId, CarModel carModel, String vinNumber, String color, boolean bought, Status status) {
        this.vehicleId = vehicleId;
        this.carModel = carModel;
        this.vinNumber = vinNumber;
        this.color = color;
        this.bought = bought;

    }

    public Car(CarModel carModel, String vinNumber, String color, Timestamp receivedDate) {
        this.carModel = carModel;
        this.vinNumber = vinNumber;
        this.color = color;
        this.receivedDate = receivedDate;
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

    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }
}
