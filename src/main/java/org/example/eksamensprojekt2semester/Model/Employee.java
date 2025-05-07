package org.example.eksamensprojekt2semester.Model;

import org.example.eksamensprojekt2semester.Enum.Role;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String shortName;
    private String email;
    private String password;
    private Role role;

    public Employee(int employeeId, String firstName, String lastName, String shortName, String email, String password, Role role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortName = shortName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getRoleValue() {
        return role.name();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRoleFromString(String role) {
        this.role = Role.fromString(role);
    }
}
