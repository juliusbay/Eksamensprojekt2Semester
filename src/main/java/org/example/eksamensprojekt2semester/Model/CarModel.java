package org.example.eksamensprojekt2semester.Model;


public class CarModel {
        private int car_model_id;
        private int model_year;
        private String brand;
        private String model;
        private String car_emission;
        private CarEquipment car_equipment;
        private double steel_price;
        private int registration_fee;


    public CarModel(int car_model_id, int model_year, String brand, String model, String car_emission, CarEquipment car_equipment, double steel_price, int registration_fee) {
        this.car_model_id = car_model_id;
        this.model_year = model_year;
        this.brand = brand;
        this.model = model;
        this.car_emission = car_emission;
        this.car_equipment = car_equipment;
        this.steel_price = steel_price;
        this.registration_fee = registration_fee;
    }

    public CarModel(int model_year, String brand, String model, String car_emission, CarEquipment car_equipment, double steel_price, int registration_fee) {
        this.model_year = model_year;
        this.brand = brand;
        this.model = model;
        this.car_emission = car_emission;
        this.car_equipment = car_equipment;
        this.steel_price = steel_price;
        this.registration_fee = registration_fee;
    }

    public int getCar_model_id() {
        return car_model_id;
    }

    public void setCar_model_id(int car_model_id) {
        this.car_model_id = car_model_id;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCar_emission() {
        return car_emission;
    }

    public void setCar_emission(String car_emission) {
        this.car_emission = car_emission;
    }

    public CarEquipment getCar_equipment() {
        return car_equipment;
    }

    public void setCar_equipment(CarEquipment car_equipment) {
        this.car_equipment = car_equipment;
    }

    public double getSteel_price() {
        return steel_price;
    }

    public void setSteel_price(double steel_price) {
        this.steel_price = steel_price;
    }

    public int getRegistration_fee() {
        return registration_fee;
    }

    public void setRegistration_fee(int registration_fee) {
        this.registration_fee = registration_fee;
    }
}


