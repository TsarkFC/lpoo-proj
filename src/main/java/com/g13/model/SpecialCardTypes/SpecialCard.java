package com.g13.model.SpecialCardTypes;

import com.g13.controller.ArenaController;
import com.g13.controller.GameParticipantController;

public class SpecialCard {
    private int cost;
    private char symbol;
    private String cardInfo;

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

    public void activate(ACTIVATION_CONDITIONS condition, ArenaController arenaController, GameParticipantController participantController, GameParticipantController oppositeController){

    }

    public int getRoundsLeft(){
        return roundsLeft;
    }
}
