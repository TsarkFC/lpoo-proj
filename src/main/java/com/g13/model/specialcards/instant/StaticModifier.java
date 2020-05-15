package com.g13.model.specialcards.instant;

import com.g13.model.specialcards.SpecialCard;

public class StaticModifier extends SpecialCard {

    private int modNum;

    public StaticModifier(int cost, char symbol, String cardInfo, int modNum) {
        super(cost, symbol, cardInfo);
        this.modNum = modNum;
        cardType = CARD_TYPE.STATIC_MODIFIER;
    }

    public int getModNum(){ return modNum; }
}
