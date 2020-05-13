package com.g13.model.SpecialCardTypes.Instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.SpecialCardTypes.SpecialCard;

import java.util.concurrent.ThreadLocalRandom;

public class FluxModifierAtoB extends SpecialCard {

    int minModNum;
    int maxModNum;

    final CARD_TYPE cardType = SpecialCard.CARD_TYPE.FLUX_MODIFIER_A_TO_B;

    public FluxModifierAtoB(int cost, char symbol, String cardInfo, int minModNum, int maxModNum) {
        super(cost, symbol, cardInfo);
        this.minModNum = minModNum;
        this.maxModNum = maxModNum;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //Add a random number --Has to be in the function itself, if it were in the constructor, it'd always add the same number
        int modNum = ThreadLocalRandom.current().nextInt(minModNum, maxModNum + 1);

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }
}
