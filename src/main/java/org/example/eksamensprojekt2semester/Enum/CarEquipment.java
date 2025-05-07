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
