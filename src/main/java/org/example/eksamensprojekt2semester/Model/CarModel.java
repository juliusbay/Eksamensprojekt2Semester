package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.FuelType;
import org.example.eksamensprojekt2semester.Enum.GearBox;

public class CarModel {

    private int carModelId;
    private String modelName;
    private String brand;
    private FuelType fuelType;
    private int modelYear;
    private GearBox gearBox;
    private int carEmission;
    private String carEquipment;
    private double steelPrice;

    public CarModel(int carModelId, String modelName, String brand, FuelType fuelType, int modelYear, GearBox gearBox, int carEmission, String carEquipment, double steelPrice) {
        this.carModelId = carModelId;
        this.modelName = modelName;
        this.brand = brand;
        this.fuelType = fuelType;
        this.modelYear = modelYear;
        this.gearBox = gearBox;
        this.carEmission = carEmission;
        this.carEquipment = carEquipment;
        this.steelPrice = steelPrice;
    }

    public CarModel() {
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(int carModelId) {
        this.carModelId = carModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelTypeFromString(String fuelType) {
        this.fuelType = FuelType.valueOf(fuelType);
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public GearBox getGearBox() {
        return gearBox;
    }

    public void setGearBox(GearBox gearBox) {
        this.gearBox = gearBox;
    }

    public void setGearBoxFromString(String gearBox) {
        this.gearBox = GearBox.valueOf(gearBox);
    }

    public int getCarEmission() {
        return carEmission;
    }

    public void setCarEmission(int carEmission) {
        this.carEmission = carEmission;
    }

    public String getCarEquipment() {
        return carEquipment;
    }

    public void setCarEquipment(String carEquipment) {
        this.carEquipment = carEquipment;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }
}


