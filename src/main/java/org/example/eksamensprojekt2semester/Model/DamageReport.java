package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class DamageReport {
    int reportId;
    int vehicleId;
    Date reportDate;
    String damageType;
    double damagePrice;
    int handledBy; // References userID

    public DamageReport(int vehicleId, Date reportDate, String damageType, double damagePrice, int handledBy) {
        this.vehicleId = vehicleId;
        this.reportDate = reportDate;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
        this.handledBy = handledBy;
    }

    public DamageReport() {}

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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
