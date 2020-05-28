package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.instant.InstantDamage;

public class AcInstantDamage extends AcSpecialCard {
    InstantDamage card;

    public AcInstantDamage(InstantDamage card) {
        super(card);
        this.card = card;
    }

    @Override
    public void activate(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();
        ParticipantController opponentController = arenaController.getOpponent();

        currentController.subtractMana(card.getCost());
        opponentController.subtractHealth(card.getDamage());
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController) {
        if (checkPlay(arenaController))
            return false;
        activate(arenaController);
        return true;
    }
}
