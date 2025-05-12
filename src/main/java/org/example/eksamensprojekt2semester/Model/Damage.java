package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class Damage {
    private int damageId;
    private int fkVehicleId;
    private int conditionReportId;
    private String damageType;
    private double damagePrice;
    private Date damageDate;

    public Damage(int damageId, int fkVehicleId, String damageType, double damagePrice, Date damageDate) {
        this.damageId = damageId;
        this.fkVehicleId = fkVehicleId;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
        this.damageDate = damageDate;
    }

    public Damage(int fkVehicleId, String damageType, double damagePrice, Date damageDate) {
        this.fkVehicleId = fkVehicleId;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
        this.damageDate = damageDate;
    }

    public Damage() {}

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    public int getFkVehicleId() {
        return fkVehicleId;
    }

    public void setFkVehicleId(int fkVehicleId) {
        this.fkVehicleId = fkVehicleId;
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

    public java.sql.Date getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(Date damageDate) {
        this.damageDate = damageDate;
    }

    public int getConditionReportId() {
        return conditionReportId;
    }

    public void setConditionReportId(int conditionReportId) {
        this.conditionReportId = conditionReportId;
    }
}
