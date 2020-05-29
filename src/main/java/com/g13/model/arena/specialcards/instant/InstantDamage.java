package com.g13.model.arena.specialcards.instant;

import com.g13.model.arena.specialcards.SpecialCard;

public class InstantDamage extends SpecialCard {
    private int damage;

    public InstantDamage(int cost, char symbol, String cardInfo, int damage) {
        super(cost, symbol, cardInfo);
        this.damage = damage;
    }

    public int getDamage() { return damage; }
}
