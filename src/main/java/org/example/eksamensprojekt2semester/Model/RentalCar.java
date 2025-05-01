package org.example.eksamensprojekt2semester.Model;

public class RentalCar {

    private int vehicle_id;
    private int car_model_id;
    private String vin_number;
    private String color;
    private String return_address;
    private double monthly_price;
    private int mileage;
    public Status status;

    public enum Status {KLAR,SKADET,TIL_KLARGØRING,UDLEJET}

    public RentalCar(int car_model_id, String vin_number, String color, String return_address, double monthly_price, int mileage, int vehicle_id) {
        this.car_model_id = car_model_id;
        this.vin_number = vin_number;
        this.color = color;
        this.return_address = return_address;
        this.monthly_price = monthly_price;
        this.mileage = mileage;
        this.vehicle_id = vehicle_id;
        status = Status.KLAR;
    }
    public RentalCar() {
        status = Status.KLAR;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public String getVin_number() {
        return vin_number;
    }

    public void setVin_number(String vin_number) {
        this.vin_number = vin_number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getReturn_address() {
        return return_address;
    }

    public void setReturn_address(String return_adress) {
        this.return_address = return_adress;
    }

    public double getMonthly_Price() {
        return monthly_price;
    }

    public void setMonthly_Price(double monthly_price) {
        this.monthly_price = monthly_price;
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
