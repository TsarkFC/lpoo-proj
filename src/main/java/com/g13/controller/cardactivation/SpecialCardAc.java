package com.g13.controller.cardactivation;

import com.g13.controller.ArenaController;
import com.g13.model.specialcards.SpecialCard;

public abstract class SpecialCardAc {

    private SpecialCard card;

    public SpecialCardAc(SpecialCard card){ this.card = card; }

    public boolean checkPlay(ArenaController arenaController) {
        return arenaController.getEnemy().getMana() < card.getCost();
    }

    public abstract void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController);
    public abstract boolean checkEnemyPlay(ArenaController arenaController);
}
