package org.example.eksamensprojekt2semester.Enum;

public enum FuelType {
    ELECTRIC("Elektrisk"),
    GASOLINE("Benzin"),
    HYBRID("Hybrid");

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
