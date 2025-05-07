package org.example.eksamensprojekt2semester.Enum;

public enum FuelType {
    ELECTRIC("elektrisk"),
    GASOLINE("benzin"),
    HYBRID("hybrid");

    private final String prettyPrint;

    FuelType(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Override
    public String toString() {
        return prettyPrint;
    }

    public static FuelType fromString(String prettyPrint){
        for (FuelType fuelType: values()) {
            if (fuelType.prettyPrint.equalsIgnoreCase(prettyPrint)){
                return fuelType;
            }
        }
        return null;
    }
}
