package org.example.eksamensprojekt2semester.Model;
//Lavet af tobias
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;
    private String address;
    private String city;
    private int postalCode;
    private String cprNumber;
    private int fkVehicleId;

    public Customer(int customerId, String firstName, String lastName, String email, int phoneNumber, String address, String city, int postalCode, String cprNumber, int fkVehicleId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.cprNumber = cprNumber;
        this.fkVehicleId = fkVehicleId;
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, int phoneNumber, String address, String city, int postalCode, String cprNumber, int fkVehicleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.cprNumber = cprNumber;
        this.fkVehicleId = fkVehicleId;
    }

    public Customer(String firstName, String lastName, String email, int phoneNumber, String address, String city, int postalCode, String cprNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.cprNumber = cprNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public int getFkVehicleId() {
        return fkVehicleId;
    }

    public void setFkVehicleId(int fkVehicleId) {
        this.fkVehicleId = fkVehicleId;
    }
}