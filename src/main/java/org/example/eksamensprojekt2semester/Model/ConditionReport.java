package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class ConditionReport {
    private int conditionReportId;
    private int fkVehicleId;
    private String handledBy;
    private Date reportStartDate;
    private Date reportCompletedDate;
    private double excessKilometers;
    private boolean completed;
    private String reportDescription;

    public ConditionReport(int conditionReportId, int fkVehicleId, String handledBy, Date reportStartDate, Date reportCompletedDate, double excessKilometers) {
        this.conditionReportId = conditionReportId;
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportStartDate = reportStartDate;
        this.reportCompletedDate = reportCompletedDate;
        this.excessKilometers = excessKilometers;
    }

    public ConditionReport(int fkVehicleId, String handledBy, Date reportStartDate) {
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportStartDate = reportStartDate;
    }

    public ConditionReport() {
    }

    public ConditionReport(int conditionReportId, String handledBy, Date reportCompletedDate, boolean completed) {
        this.conditionReportId = conditionReportId;
        this.handledBy = handledBy;
        this.reportCompletedDate = reportCompletedDate;
        this.completed = completed;
    }

    public ConditionReport(int conditionReportId, String handledBy, Date reportCompletedDate, boolean completed, String reportDescription) {
        this.conditionReportId = conditionReportId;
        this.handledBy = handledBy;
        this.reportCompletedDate = reportCompletedDate;
        this.completed = completed;
        this.reportDescription = reportDescription;
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

    public Date getReportStartDate() {
        return reportStartDate;
    }

    public void setReportStartDate(Date reportStartDate) {
        this.reportStartDate = reportStartDate;
    }

    public Date getReportCompletedDate() {
        return reportCompletedDate;
    }

    public void setReportCompletedDate(Date reportCompletedDate) {
        this.reportCompletedDate = reportCompletedDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
}

