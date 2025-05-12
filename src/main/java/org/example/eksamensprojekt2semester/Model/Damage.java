package org.example.eksamensprojekt2semester.Model;

public class Damage {
    private int damageId;
    private int conditionReportId;
    private String damageType;
    private double damagePrice;

    public Damage(int conditionReportId, String damageType, double damagePrice) {
        this.conditionReportId = conditionReportId;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
    }

    public Damage(String damageType, double damagePrice) {
        this.damageType = damageType;
        this.damagePrice = damagePrice;
    }

    public Damage(int damageId, int conditionReportId, String damageType, double damagePrice) {
        this.damageId = damageId;
        this.conditionReportId = conditionReportId;
        this.damageType = damageType;
        this.damagePrice = damagePrice;
    }


    public Damage() {}

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
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

    public int getConditionReportId() {
        return conditionReportId;
    }

    public void setConditionReportId(int conditionReportId) {
        this.conditionReportId = conditionReportId;
    }
}
