package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.instant.InstantDamage;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.max;

public class AcInstantDamage extends AcSpecialCard {
    public InstantDamage card;

    public AcInstantDamage(InstantDamage card){
        super(card);
        this.card = card;
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController){
        if (checkPlay(arenaController))
            return false;

        //if(arenaController.getEnemy().getPoints() + card.getModNum() > arenaController.getEnemy().getMaxPoints())
        //    return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckInstantDamage(arenaController, card.getCost(), card.getMinDamage(), card.getMaxDamage()))
            return false;

        activate(arenaController);
        return true;
    }

    @Override
    public void activate(ArenaController arenaController){
        ParticipantController currentController = arenaController.getCurrent();
        ParticipantController opponentController = arenaController.getOpponent();
        //Subtract mana
        currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
        //currentController.setPoints(currentController.getPoints() + card.getModNum());
        //arenaController.checkControllerPoints();
        opponentController.setHealth(max(0, opponentController.getHealth() - ThreadLocalRandom.current().nextInt(card.getMinDamage(), card.getMaxDamage() + 1)));
    }
}
