package org.example.eksamensprojekt2semester.Enum;

public enum Status {
    READY("klar"),
    READY_FOR_BUYER("klar til køber"),
    GETTING_REPAIRED("på værksted"),
    RENTED("udlejet");

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
