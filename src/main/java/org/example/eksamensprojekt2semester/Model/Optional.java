package org.example.eksamensprojekt2semester.Model;

public class Optional {
    private int optionalId;
    private double cleverUnlimitedNetwork;
    private double cleverUnlimitedHybrid;
    private double cleverUnlimited;
    private double colorPrice;
    private double vikingRoadHelp;
    private double greenTax;
    private double lowSelfInsurance;
    private double initialPayment;
    private double monthlyKilometers;
    private double dropOffInsurance;
    private double tyreRental;

    public Optional(int optionalId, double cleverUnlimitedNetwork, double cleverUnlimitedHybrid, double cleverUnlimited,
                    double colorPrice, double vikingRoadHelp, double greenTax, double lowSelfInsurance,
                    double initialPayment, double monthlyKilometers, double dropOffInsurance, double tyreRental) {
        this.optionalId = optionalId;
        this.cleverUnlimitedNetwork = cleverUnlimitedNetwork;
        this.cleverUnlimitedHybrid = cleverUnlimitedHybrid;
        this.cleverUnlimited = cleverUnlimited;
        this.colorPrice = colorPrice;
        this.vikingRoadHelp = vikingRoadHelp;
        this.greenTax = greenTax;
        this.lowSelfInsurance = lowSelfInsurance;
        this.initialPayment = initialPayment;
        this.monthlyKilometers = monthlyKilometers;
        this.dropOffInsurance = dropOffInsurance;
        this.tyreRental = tyreRental;
    }

    public Optional(double cleverUnlimitedNetwork, double cleverUnlimitedHybrid, double cleverUnlimited,
                    double colorPrice, double vikingRoadHelp, double greenTax, double lowSelfInsurance,
                    double initialPayment, double monthlyKilometers, double dropOffInsurance, double tyreRental) {
        this.cleverUnlimitedNetwork = cleverUnlimitedNetwork;
        this.cleverUnlimitedHybrid = cleverUnlimitedHybrid;
        this.cleverUnlimited = cleverUnlimited;
        this.colorPrice = colorPrice;
        this.vikingRoadHelp = vikingRoadHelp;
        this.greenTax = greenTax;
        this.lowSelfInsurance = lowSelfInsurance;
        this.initialPayment = initialPayment;
        this.monthlyKilometers = monthlyKilometers;
        this.dropOffInsurance = dropOffInsurance;
        this.tyreRental = tyreRental;
    }

    public Optional() {}

    public int getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(int optionalId) {
        this.optionalId = optionalId;
    }

    public double getCleverUnlimitedNetwork() {
        return cleverUnlimitedNetwork;
    }

    public void setCleverUnlimitedNetwork(double cleverUnlimitedNetwork) {
        this.cleverUnlimitedNetwork = cleverUnlimitedNetwork;
    }

    public double getCleverUnlimitedHybrid() {
        return cleverUnlimitedHybrid;
    }

    public void setCleverUnlimitedHybrid(double cleverUnlimitedHybrid) {
        this.cleverUnlimitedHybrid = cleverUnlimitedHybrid;
    }

    public double getCleverUnlimited() {
        return cleverUnlimited;
    }

    public void setCleverUnlimited(double cleverUnlimited) {
        this.cleverUnlimited = cleverUnlimited;
    }

    public double getColorPrice() {
        return colorPrice;
    }

    public void setColorPrice(double colorPrice) {
        this.colorPrice = colorPrice;
    }

    public double getVikingRoadHelp() {
        return vikingRoadHelp;
    }

    public void setVikingRoadHelp(double vikingRoadHelp) {
        this.vikingRoadHelp = vikingRoadHelp;
    }

    public double getGreenTax() {
        return greenTax;
    }

    public void setGreenTax(double greenTax) {
        this.greenTax = greenTax;
    }

    public double getLowSelfInsurance() {
        return lowSelfInsurance;
    }

    public void setLowSelfInsurance(double lowSelfInsurance) {
        this.lowSelfInsurance = lowSelfInsurance;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(double initialPayment) {
        this.initialPayment = initialPayment;
    }

    public double getMonthlyKilometers() {
        return monthlyKilometers;
    }

    public void setMonthlyKilometers(double monthlyKilometers) {
        this.monthlyKilometers = monthlyKilometers;
    }

    public double getDropOffInsurance() {
        return dropOffInsurance;
    }

    public void setDropOffInsurance(double dropOffInsurance) {
        this.dropOffInsurance = dropOffInsurance;
    }

    public double getTyreRental() {
        return tyreRental;
    }

    public void setTyreRental(double tyreRental) {
        this.tyreRental = tyreRental;
    }
}
