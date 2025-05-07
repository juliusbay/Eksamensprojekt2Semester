package org.example.eksamensprojekt2semester.Model;

public class Choice {
    int choiceId;
    String choiceName;
    double choicePrice;

    public Choice(int choiceId, String choiceName, double choicePrice) {
        this.choiceId = choiceId;
        this.choiceName = choiceName;
        this.choicePrice = choicePrice;
    }

    public Choice() {
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public double getChoicePrice() {
        return choicePrice;
    }

    public void setChoicePrice(double choicePrice) {
        this.choicePrice = choicePrice;
    }
}
