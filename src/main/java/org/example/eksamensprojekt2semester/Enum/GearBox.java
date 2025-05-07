package org.example.eksamensprojekt2semester.Enum;

public enum GearBox {
    AUTOMATIC("automatisk"),
    MANUAL("manuel");

    private final String prettyPrint;

    GearBox(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Override
    public String toString() {
        return prettyPrint;
    }

    public static GearBox fromString(String prettyPrint){
        for (GearBox gearBox: values()) {
            if (gearBox.prettyPrint.equalsIgnoreCase(prettyPrint)){
                return gearBox;
            }
        }
        return null;
    }
}
