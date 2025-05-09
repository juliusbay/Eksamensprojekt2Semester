package org.example.eksamensprojekt2semester.Model;

public class ChosenChoice {
    int fkChoiceId;
    int fkLeaseAgreementId;

    public ChosenChoice(int fkChoiceId, int fkLeaseAgreementId) {
        this.fkChoiceId = fkChoiceId;
        this.fkLeaseAgreementId = fkLeaseAgreementId;
    }

    public ChosenChoice() {
    }

    public int getFkChoiceId() {
        return fkChoiceId;
    }

    public void setFkChoiceId(int fkChoiceId) {
        this.fkChoiceId = fkChoiceId;
    }

    public int getFkLeaseAgreementId() {
        return fkLeaseAgreementId;
    }

    public void setFkLeaseAgreementId(int fkLeaseAgreementId) {
        this.fkLeaseAgreementId = fkLeaseAgreementId;
    }
}
