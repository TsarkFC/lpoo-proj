package com.g13.model.arena.specialcards.instant;

import com.g13.model.arena.specialcards.SpecialCard;

public class FluxModifierAtoB extends SpecialCard {

    private int minModNum;
    private int maxModNum;

    public FluxModifierAtoB(int cost, char symbol, String cardInfo, int minModNum, int maxModNum) {
        super(cost, symbol, cardInfo);
        this.minModNum = minModNum;
        this.maxModNum = maxModNum;
    }

    public int getMinModNum(){ return minModNum; }
    public int getMaxModNum(){ return maxModNum; }
}
