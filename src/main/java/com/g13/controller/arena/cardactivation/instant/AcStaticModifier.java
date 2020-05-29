package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.instant.StaticModifier;

public class AcStaticModifier extends AcSpecialCard {
    private StaticModifier card;

    public AcStaticModifier(StaticModifier card){
        super(card);
        this.card = card;
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController, int cardPos){
        if (checkPlay(arenaController))
            return false;

        if(arenaController.getEnemy().getPoints() + card.getModNum() > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckStaticModifier(arenaController, card.getCost(), card.getModNum()))
            return false;

        RotateCards(arenaController, cardPos);
        activate(arenaController);
        return true;
    }

    @Override
    public void activate(ArenaController arenaController){
        ParticipantController currentController = arenaController.getCurrent();

        currentController.subtractMana(card.getCost());
        currentController.setPoints(currentController.getPoints() + card.getModNum());
        arenaController.checkControllerPoints();
    }
}
