package org.example.eksamensprojekt2semester.Model;

public class Car {

    private int vehicleId;
    private int carModelId;
    private String vinNumber;
    private String color;
    private String returnAddress;
    private double monthlyPrice;
    private int mileage;
    public Status status;

    public enum Status {KLAR,SKADET,TIL_KLARGØRING,UDLEJET}

    public Car(int carModelId, String vinNumber, String color, String returnAddress, double monthlyPrice, int mileage, int vehicleId) {
        this.carModelId = carModelId;
        this.vinNumber = vinNumber;
        this.color = color;
        this.returnAddress = returnAddress;
        this.monthlyPrice = monthlyPrice;
        this.mileage = mileage;
        this.vehicleId = vehicleId;
        status = Status.KLAR;
    }
    public Car() {
        status = Status.KLAR;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
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

    public String getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(String return_adress) {
        this.returnAddress = return_adress;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(double monthly_price) {
        this.monthlyPrice = monthly_price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
