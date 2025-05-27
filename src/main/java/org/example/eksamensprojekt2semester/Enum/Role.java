package org.example.eksamensprojekt2semester.Enum;

// Enum designet og sat op af Tobias
public enum Role {
    ADMIN("Admin"),
    BUSINESS_DEVELOPER("Forretningsudvikler"),
    DATA_RECORDING("Dataregistrering"),
    MECHANIC("Mekaniker");

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
