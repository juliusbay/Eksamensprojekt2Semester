package org.example.eksamensprojekt2semester.Enum;

public enum Role {
    ADMIN("admin"),
    BUSINESS_DEVELOPER("foretningsudvikler"),
    DATA_RECORDING("dataregistrering"),
    MECHANIC("mekaniker");

    private final String prettyPrint;

    Role(String prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Override
    public String toString() {
        return prettyPrint;
    }

    public static Role fromString(String prettyPrint){
        for (Role role: values()) {
            if (role.prettyPrint.equalsIgnoreCase(prettyPrint)){
                return role;
            }
        }
        return null;
    }
}
