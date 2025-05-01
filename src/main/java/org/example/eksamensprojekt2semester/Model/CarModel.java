package org.example.eksamensprojekt2semester.Model;


public class CarModel {

    public enum CarEquipment {
        LAPRIMA("La Prima"),
        SPORT("Sport"),
        ADVANCE("Advance"),
        PERFORMANCE("Performance"),
        ROCK("Rock"),
        TECHNO("Techno"),
        ICON("Icon"),
        LONGRANGE("Long range"),
        VAREBIL("Varebil");

        private final String dbValue;

        CarEquipment(String dbValue) {
            this.dbValue = dbValue;
        }

        //Changes "LAPRIMA" to "La Prima"
        @Override
        public String toString() {
            return dbValue;
        }

        public static CarEquipment fromString(String value) {
            for (CarEquipment ce : CarEquipment.values()) {
                if (ce.dbValue.equalsIgnoreCase(value)) {
                    return ce;
                }
            }
            throw new IllegalArgumentException("Unknown equipment: " + value);
        }
    }

    private int carModelId;
    private int modelYear;
    private String brand;
    private String model;
    private int carEmission;
    private double steelPrice;
    private int registrationFee;
    private CarEquipment carEquipment;

    public CarModel(int carModelId, int modelYear, String brand, String model, int carEmission, CarEquipment carEquipment, double steelPrice, int registrationFee) {
        this.carModelId = carModelId;
        this.modelYear = modelYear;
        this.brand = brand;
        this.model = model;
        this.carEmission = carEmission;
        this.carEquipment = carEquipment;
        this.steelPrice = steelPrice;
        this.registrationFee = registrationFee;
    }

    public CarModel(int modelYear, String brand, String model, int carEmission, CarEquipment carEquipment, double steelPrice, int registrationFee) {
        this.modelYear = modelYear;
        this.brand = brand;
        this.model = model;
        this.carEmission = carEmission;
        this.carEquipment = carEquipment;
        this.steelPrice = steelPrice;
        this.registrationFee = registrationFee;
    }

    public CarModel() {}

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
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

    public int getCarEmission() {
        return carEmission;
    }

    public void setCarEmission(int carEmission) {
        this.carEmission = carEmission;
    }

    public CarEquipment getCarEquipment() {
        return carEquipment;
    }

    public void setCarEquipment(CarEquipment carEquipment) {
        this.carEquipment = carEquipment;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }
}


