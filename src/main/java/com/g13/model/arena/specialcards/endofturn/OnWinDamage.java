package com.g13.model.arena.specialcards.endofturn;

import com.g13.model.arena.specialcards.SpecialCard;

public class OnWinDamage extends SpecialCard {
    private int damage;
    private int numrounds;

    public OnWinDamage(int cost, char symbol, String cardInfo, int damage) {
        super(cost, symbol, cardInfo);
        this.damage = damage;
    }

    public int getDamage() { return damage; }
}
