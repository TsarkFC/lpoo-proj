package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;

import java.util.concurrent.ThreadLocalRandom;

public class AcFluxModifierAtoB extends AcSpecialCard {
    FluxModifierAtoB card;

    public AcFluxModifierAtoB(FluxModifierAtoB card){
        super(card);
        this.card = card;
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController){
        if (checkPlay(arenaController))
            return false;

        if(arenaController.getEnemy().getPoints() + card.getMinModNum() > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckFluxModifier(arenaController, card.getCost(), card.getMinModNum(), card.getMaxModNum()))
            return false;

        activate(arenaController);
        return true;
    }

    @Override
    public void activate(ArenaController arenaController){
        ParticipantController currentController = arenaController.getCurrent();

        //Add a random number --Has to be in the function itself, if it were in the constructor, it'd always add the same number
        int modNum = ThreadLocalRandom.current().nextInt(card.getMinModNum(), card.getMaxModNum() + 1);

        currentController.subtractMana(card.getCost());
        currentController.setPoints(currentController.getPoints() + modNum);
        arenaController.checkControllerPoints();
    }
}
