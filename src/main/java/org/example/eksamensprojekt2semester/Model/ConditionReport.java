package org.example.eksamensprojekt2semester.Model;

import java.sql.Timestamp;

public class ConditionReport {
    private int conditionReportId;
    private int fkVehicleId;
    private String handledBy;
    private Timestamp reportStartDate;
    private Timestamp reportCompletedDate;
    private double excessKilometers;
    private boolean completed;
    private String reportDescription;

    public ConditionReport(int fkVehicleId, String handledBy, Timestamp reportStartDate) {
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportStartDate = reportStartDate;
    }

    public ConditionReport() {
    }

    public ConditionReport(int conditionReportId, String handledBy, Timestamp reportCompletedDate, boolean completed) {
        this.conditionReportId = conditionReportId;
        this.handledBy = handledBy;
        this.reportCompletedDate = reportCompletedDate;
        this.completed = completed;
    }


    public int getConditionReportId() {
        return conditionReportId;
    }

    public void setConditionReportId(int conditionReportId) {
        this.conditionReportId = conditionReportId;
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

    public double getExcessKilometers() {
        return excessKilometers;
    }

    public void setExcessKilometers(double excessKilometers) {
        this.excessKilometers = excessKilometers;
    }

    public Timestamp getReportStartDate() {
        return reportStartDate;
    }

    public void setReportStartDate(Timestamp reportStartDate) {
        this.reportStartDate = reportStartDate;
    }

    public Timestamp getReportCompletedDate() {
        return reportCompletedDate;
    }

    public void setReportCompletedDate(Timestamp reportCompletedDate) {
        this.reportCompletedDate = reportCompletedDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportDescription() {
        return reportDescription;
    }
}

