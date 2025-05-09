package org.example.eksamensprojekt2semester.Model;

public class AvailableChoice {
    int fkCarModelId;
    int fkChoiceId;

    public AvailableChoice(int fkCarModelId, int fkChoiceId) {
        this.fkCarModelId = fkCarModelId;
        this.fkChoiceId = fkChoiceId;
    }

    public AvailableChoice() {
    }

    public int getFkCarModelId() {
        return fkCarModelId;
    }

    public void setFkCarModelId(int fkCarModelId) {
        this.fkCarModelId = fkCarModelId;
    }

    public int getFkChoiceId() {
        return fkChoiceId;
    }

    public void setFkChoiceId(int fkChoiceId) {
        this.fkChoiceId = fkChoiceId;
    }
}
