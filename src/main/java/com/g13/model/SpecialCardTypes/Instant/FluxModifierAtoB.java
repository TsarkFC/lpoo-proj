package com.g13.model.SpecialCardTypes.Instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.SpecialCardTypes.SpecialCard;

import java.util.concurrent.ThreadLocalRandom;

public class FluxModifierAtoB extends SpecialCard {

    int minModNum;
    int maxModNum;

    public FluxModifierAtoB(int cost, char symbol, String cardInfo, int minModNum, int maxModNum) {
        super(cost, symbol, cardInfo);
        this.minModNum = minModNum;
        this.maxModNum = maxModNum;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController, ParticipantController participantController, ParticipantController oppositeController){
        int modNum = ThreadLocalRandom.current().nextInt(minModNum, maxModNum + 1);

        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            participantController.setPoints(participantController.getPoints() + modNum);
            arenaController.checkControllerPoints(participantController, oppositeController);
        }
    }
}
