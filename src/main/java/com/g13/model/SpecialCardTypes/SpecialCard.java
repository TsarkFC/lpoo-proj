package com.g13.model.SpecialCardTypes;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;

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

    public void activate(ACTIVATION_CONDITIONS condition, ArenaController arenaController,
                         ParticipantController participantController, ParticipantController oppositeController){
    }

    public int getRoundsLeft(){
        return roundsLeft;
    }
}
