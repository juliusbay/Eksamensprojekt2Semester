package org.example.eksamensprojekt2semester.Model;

public class PurchaseAgreement {

        private int purchaseAgreementId;
        private int vehicleId;
        private int customerId;
        private boolean advanceBuyer;
        private double carPrice;

        public PurchaseAgreement(int purchaseAgreementId, int vehicleId, int customerId, boolean advanceBuyer, double carPrice) {
            this.purchaseAgreementId = purchaseAgreementId;
            this.vehicleId = vehicleId;
            this.customerId = customerId;
            this.advanceBuyer = advanceBuyer;
            this.carPrice = carPrice;
        }

        public PurchaseAgreement(int vehicleId, int customerId, boolean advanceBuyer, double carPrice) {
            this.vehicleId = vehicleId;
            this.customerId = customerId;
            this.advanceBuyer = advanceBuyer;
            this.carPrice = carPrice;
        }

        public PurchaseAgreement() {}

        public int getPurchaseAgreementId() {
            return purchaseAgreementId;
        }

        public void setPurchaseAgreementId(int purchaseAgreementId) {
            this.purchaseAgreementId = purchaseAgreementId;
        }

        public int getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(int vehicleId) {
            this.vehicleId = vehicleId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public boolean isAdvanceBuyer() {
            return advanceBuyer;
        }

        public void setAdvanceBuyer(boolean advanceBuyer) {
            this.advanceBuyer = advanceBuyer;
        }

        public double getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(double carPrice) {
            this.carPrice = carPrice;
        }
    }

