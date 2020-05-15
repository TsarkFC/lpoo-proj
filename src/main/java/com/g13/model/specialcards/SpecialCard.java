package com.g13.model.specialcards;

import com.g13.controller.ArenaController;

public class SpecialCard {
    private int cost;
    private char symbol;
    private String cardInfo;
    private boolean selected = false;

    protected int roundsLeft = 0;

    public enum ACTIVATION_CONDITIONS{
        ON_PLAY,
        ON_END_TURN
    }

    public enum CARD_TYPE{
        HEAL_INSTANT,
        HEAL_ON_END_TURN,
        FLUX_MODIFIER_A_TO_B,
        FLUX_MODIFIER_X_Y_OR_Z,
        STATIC_MODIFIER
    }

    protected CARD_TYPE cardType;

    protected int duration = 0;

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


    public CARD_TYPE getCardType(){
        return cardType;
    }
}
