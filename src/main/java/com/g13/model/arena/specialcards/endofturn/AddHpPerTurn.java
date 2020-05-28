package com.g13.model.arena.specialcards.endofturn;

import com.g13.model.arena.specialcards.SpecialCard;

public class AddHpPerTurn extends SpecialCard {

    private int HPPerTurn;

    public AddHpPerTurn(int cost, char symbol, String cardInfo, int HPPerTurn, int numRounds) {
        super(cost, symbol, cardInfo);
        this.HPPerTurn = HPPerTurn;
        this.roundsLeft = numRounds;
    }

    public int getHPPerTurn(){ return HPPerTurn; }

}
