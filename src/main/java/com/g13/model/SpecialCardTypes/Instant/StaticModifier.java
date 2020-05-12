package com.g13.model.SpecialCardTypes.Instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.SpecialCardTypes.SpecialCard;

public class StaticModifier extends SpecialCard {

    int modNum;

    public StaticModifier(int cost, char symbol, String cardInfo, int modNum) {
        super(cost, symbol, cardInfo);
        this.modNum = modNum;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController, ParticipantController participantController, ParticipantController oppositeController){

        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            participantController.setPoints(participantController.getPoints() + modNum);
            arenaController.checkControllerPoints(participantController, oppositeController);
        }
    }

}
