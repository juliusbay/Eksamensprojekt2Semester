package org.example.eksamensprojekt2semester.Enum;

public enum Status {
    READY("Klar"),
    READY_FOR_TRANSPORT("Klar til transport"),
    GETTING_REPAIRED("På værksted"),
    RENTED("Udlejet");

    private final String prettyPrint;

    Status(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Override
    public String toString() {
        return prettyPrint;
    }

    public static Status fromString(String prettyPrint){
        for (Status status: values()) {
            if (status.prettyPrint.equalsIgnoreCase(prettyPrint)){
                return status;
            }
        }
        return null;
    }
}
