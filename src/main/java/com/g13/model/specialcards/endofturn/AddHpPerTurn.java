package com.g13.model.specialcards.endofturn;

import com.g13.model.specialcards.SpecialCard;

public class AddHpPerTurn extends SpecialCard {

    private int HPPerTurn;

    public AddHpPerTurn(int cost, char symbol, String cardInfo, int HPPerTurn, int numRounds) {
        super(cost, symbol, cardInfo);
        this.HPPerTurn = HPPerTurn;
        this.roundsLeft = numRounds;
        cardType = SpecialCard.CARD_TYPE.HEAL_ON_END_TURN;
    }

    public int getHPPerTurn(){ return HPPerTurn; }

}
