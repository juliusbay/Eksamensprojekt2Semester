package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class DamageReport {
    int reportID;
    int vehicleID;
    Date reportDate;
    String damageType;
    double damagePrice;
    int handledBy; // References userID

    public DamageReport(int vehicleID, Date reportDate, String damageType, double damagePrice, int handledBy) {
        this.vehicleID = vehicleID;
        this.reportDate = reportDate;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
        this.handledBy = handledBy;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public double getDamagePrice() {
        return damagePrice;
    }

    public void setDamagePrice(double damagePrice) {
        this.damagePrice = damagePrice;
    }

    public int getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(int handledBy) {
        this.handledBy = handledBy;
    }
}
