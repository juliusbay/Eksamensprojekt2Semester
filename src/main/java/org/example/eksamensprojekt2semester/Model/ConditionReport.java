package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class ConditionReport {
    int ConditionReportId;
    int fkDamageId;
    int fkVehicleId;
    String handledBy;
    Date reportDate;

    public ConditionReport(int conditionReportId, int fkDamageId, int fkVehicleId, String handledBy, Date reportDate) {
        ConditionReportId = conditionReportId;
        this.fkDamageId = fkDamageId;
        this.fkVehicleId = fkVehicleId;
        this.handledBy = handledBy;
        this.reportDate = reportDate;
    }

    public ConditionReport() {
    }

    public int getConditionReportId() {
        return ConditionReportId;
    }

    public void setConditionReportId(int conditionReportId) {
        ConditionReportId = conditionReportId;
    }

    public int getFkDamageId() {
        return fkDamageId;
    }

    public void setFkDamageId(int fkDamageId) {
        this.fkDamageId = fkDamageId;
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
}

