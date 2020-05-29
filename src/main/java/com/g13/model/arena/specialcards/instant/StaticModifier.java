package com.g13.model.arena.specialcards.instant;

import com.g13.model.arena.specialcards.SpecialCard;

public class StaticModifier extends SpecialCard {

    private int modNum;

    public StaticModifier(int cost, char symbol, String cardInfo, int modNum) {
        super(cost, symbol, cardInfo);
        this.modNum = modNum;
    }

    public int getModNum(){ return modNum; }
}
