package com.g13.model.arena.specialcards.instant;

import com.g13.model.arena.specialcards.SpecialCard;

public class InstantDamage extends SpecialCard {
    private int minDamage;
    private int maxDamage;

    public InstantDamage(int cost, char symbol, String cardInfo, int minDamage, int maxDamage) {
        super(cost, symbol, cardInfo);
        cardType = SpecialCard.CARD_TYPE.INSTANT_DAMAGE;
    }

    public int getMinDamage(){ return minDamage; }
    public int getMaxDamage(){ return maxDamage; }
}
