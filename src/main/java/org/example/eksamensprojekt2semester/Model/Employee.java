package org.example.eksamensprojekt2semester.Model;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String shortName;
    private String email;
    private String password;
    private Role role;

    public enum Role {ADMIN, FORRETNINGSUDVIKLER, DATAREGISTRERING, MEKANIKER};

    public Employee(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        shortName = (firstName.substring(0,2) + lastName.substring(0,3)).toUpperCase() + employeeId;
    }

    public Employee(){
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int userId) {
        this.employeeId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
