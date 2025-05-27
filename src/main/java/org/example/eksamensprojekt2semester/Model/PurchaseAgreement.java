package org.example.eksamensprojekt2semester.Model;
// Lavet af Frederik
public class PurchaseAgreement {

        private int purchaseAgreementId;
        private int fkVehicleId;
        private int fkCustomerId;
        private boolean paid;
        private double carPrice;
        private Customer customer;
        private Car car;

        public PurchaseAgreement(int purchaseAgreementId, int fkVehicleId, int fkCustomerId, boolean paid, double carPrice) {
            this.purchaseAgreementId = purchaseAgreementId;
            this.fkVehicleId = fkVehicleId;
            this.fkCustomerId = fkCustomerId;
            this.paid = paid;
            this.carPrice = carPrice;
        }

        public PurchaseAgreement(int fkVehicleId, int fkCustomerId, boolean paid, double carPrice) {
            this.fkVehicleId = fkVehicleId;
            this.fkCustomerId = fkCustomerId;
            this.paid = paid;
            this.carPrice = carPrice;
        }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public PurchaseAgreement() {}

        public int getPurchaseAgreementId() {
            return purchaseAgreementId;
        }

        public void setPurchaseAgreementId(int purchaseAgreementId) {
            this.purchaseAgreementId = purchaseAgreementId;
        }

        public int getFkVehicleId() {
            return fkVehicleId;
        }

        public void setFkVehicleId(int fkVehicleId) {
            this.fkVehicleId = fkVehicleId;
        }

        public int getFkCustomerId() {
            return fkCustomerId;
        }

        public void setFkCustomerId(int fkCustomerId) {
            this.fkCustomerId = fkCustomerId;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public double getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(double carPrice) {
            this.carPrice = carPrice;
        }

        public void setCustomer(Customer customer) {
        this.customer = customer;
    }
        public Customer getCustomer() {
            return customer;
    }
}

