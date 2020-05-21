package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.SpecialCardAc;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.StaticModifier;

public class StaticModifierAc extends SpecialCardAc {
    private StaticModifier card;

    public StaticModifierAc(StaticModifier card){
        super(card);
        this.card = card;
    }

    public boolean checkEnemyPlay(ArenaController arenaController){
        checkPlay(arenaController);

        if(arenaController.getEnemy().getPoints() + card.getModNum() > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckStaticModifier(arenaController, card.getCost(), card.getModNum()))
            return false;

        activate(SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
            currentController.setPoints(currentController.getPoints() + card.getModNum());
            arenaController.checkControllerPoints();
        }
    }
}
