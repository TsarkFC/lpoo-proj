package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.SpecialCardAc;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;

import java.util.concurrent.ThreadLocalRandom;

public class FluxModifierAtoBAc extends SpecialCardAc {
    FluxModifierAtoB card;

    public FluxModifierAtoBAc(FluxModifierAtoB card){
        super(card);
        this.card = card;
    }

    public boolean checkEnemyPlay(ArenaController arenaController){
        checkPlay(arenaController);

        if(arenaController.getEnemy().getPoints() + card.getMinModNum() > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckFluxModifier(arenaController, card.getCost(), card.getMinModNum(), card.getMaxModNum()))
            return false;

        activate(SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //Add a random number --Has to be in the function itself, if it were in the constructor, it'd always add the same number
        int modNum = ThreadLocalRandom.current().nextInt(card.getMinModNum(), card.getMaxModNum() + 1);

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }
}
