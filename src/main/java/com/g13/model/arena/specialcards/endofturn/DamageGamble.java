package com.g13.model.arena.specialcards.endofturn;

import com.g13.model.arena.specialcards.SpecialCard;

public class DamageGamble extends SpecialCard {
    private int manaPerDamageTaken;

    public DamageGamble(int cost, char symbol, String cardInfo, int manaPerDamageTaken) {
        super(cost, symbol, cardInfo);
        this.manaPerDamageTaken = manaPerDamageTaken;
    }

    public int getmanaPerDamageTaken() { return manaPerDamageTaken; }
}
