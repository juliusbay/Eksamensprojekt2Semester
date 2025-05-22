package org.example.eksamensprojekt2semester.Enum;

public enum CarEquipment {
    LAPRIMA("La Prima"),
    SPORT("Sport"),
    ADVANCE("Advance"),
    PERFORMANCE("Performance"),
    ROCK("Rock"),
    TECHNO("Techno"),
    ICON("Icon"),
    LONG_RANGE("Long range"),
    VAREBIL("Varebil");

    private final String prettyprint;

    CarEquipment(String prettyprint) {
        this.prettyprint = prettyprint;
    }

    //Changes "LAPRIMA" to "La Prima"
    @Override
    public String toString() {
        return prettyprint;
    }

    public static CarEquipment fromString(String value) {
        for (CarEquipment carEquipment : CarEquipment.values()) {
            if (carEquipment.prettyprint.equalsIgnoreCase(value)) {
                return carEquipment;
            }
        }
        return null;
    }
}
