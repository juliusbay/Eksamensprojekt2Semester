package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class VehicleReturns {
    private int returnId;
    private int vehicleId;
    private Date returnDate;
    private ReturnedTo returnedTo;
    private int reportId;
    public enum ReturnedTo {FDM,Bilabonnement}

    public VehicleReturns(int returnId, int vehicleId, Date returnDate, ReturnedTo returnedTo, int reportId) {
        this.returnId = returnId;
        this.vehicleId = vehicleId;
        this.returnDate = returnDate;
        this.returnedTo = returnedTo;
        this.reportId = reportId;
    }
    public VehicleReturns() {

    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ReturnedTo getReturnedTo() {
        return returnedTo;
    }

    public void setReturnedTo(ReturnedTo returnedTo) {
        this.returnedTo = returnedTo;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
