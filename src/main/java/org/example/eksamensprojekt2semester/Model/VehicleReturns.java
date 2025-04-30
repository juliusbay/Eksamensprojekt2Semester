package org.example.eksamensprojekt2semester.Model;

import java.sql.Date;

public class VehicleReturns {
    private int return_id;
    private int vehicle_id;
    private Date return_date;
    private ReturnedTo returnedTo;
    private int report_id;
    public enum ReturnedTo {FDM,Bilabonnement}

    public VehicleReturns(int return_id, int vehicle_id, Date return_date, ReturnedTo returnedTo, int report_id) {
        this.return_id = return_id;
        this.vehicle_id = vehicle_id;
        this.return_date = return_date;
        this.returnedTo = returnedTo;
        this.report_id = report_id;
    }

    public int getReturn_id() {
        return return_id;
    }

    public void setReturn_id(int return_id) {
        this.return_id = return_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public ReturnedTo getReturnedTo() {
        return returnedTo;
    }

    public void setReturnedTo(ReturnedTo returnedTo) {
        this.returnedTo = returnedTo;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }
}
