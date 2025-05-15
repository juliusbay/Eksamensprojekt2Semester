package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class ConditionReport {
    private int ConditionReportId;
    private int fkVehicleId;
    private String handledBy;
    private Date reportDate;
    private double excessKilometers;

    public ConditionReport(int conditionReportId, int fkVehicleId, String handledBy, Date reportDate) {
        ConditionReportId = conditionReportId;
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportDate = reportDate;
    }

    public ConditionReport() {
    }

    public ConditionReport(int fkVehicleId) {
        this.fkVehicleId = fkVehicleId;
    }

    public ConditionReport(int fkVehicleId, String handledBy, Date reportDate) {
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportDate = reportDate;
    }

    public ConditionReport(int fkVehicleId, String handledBy, Date reportDate, double excessKilometers) {
        this.excessKilometers = excessKilometers;
        this.reportDate = reportDate;
        this.handledBy = handledBy;
        this.fkVehicleId = fkVehicleId;
    }

    public ConditionReport(int conditionReportId, int fkVehicleId, String handledBy, Date reportDate, double excessKilometers) {
        ConditionReportId = conditionReportId;
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportDate = reportDate;
        this.excessKilometers = excessKilometers;
    }

    public int getConditionReportId() {
        return ConditionReportId;
    }

    public void setConditionReportId(int conditionReportId) {
        ConditionReportId = conditionReportId;
    }

    public int getFkVehicleId() {
        return fkVehicleId;
    }

    public void setFkVehicleId(int fkVehicleId) {
        this.fkVehicleId = fkVehicleId;
    }

    public String getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(String handledBy) {
        this.handledBy = handledBy;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public double getExcessKilometers() {
        return excessKilometers;
    }

    public void setExcessKilometers(double excessKilometers) {
        this.excessKilometers = excessKilometers;
    }
}

