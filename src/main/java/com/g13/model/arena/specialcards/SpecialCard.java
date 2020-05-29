package com.g13.model.arena.specialcards;

public class SpecialCard {
    private int cost;
    private char symbol;
    private String cardInfo;
    private boolean selected = false;

    protected int roundsLeft;

    public SpecialCard(int cost, char symbol, String cardInfo){
        this.cost = cost;
        this.symbol = symbol;
        this.cardInfo = cardInfo;
        this.roundsLeft = 0;
    }

    public int getCost() {
        return cost;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public boolean getSelected() {return selected;}
    public void setSelected(boolean selected) { this.selected = selected; }

    public int getRoundsLeft(){
        return roundsLeft;
    }
    public void decrementRoundsLeft() {roundsLeft--;}
}
