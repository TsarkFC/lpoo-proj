package com.g13.controller.commands;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.specialcards.SpecialCard;

public class PlaySpecialCardCommand {
    private ArenaController arenaController;
    private ParticipantController currentController;
    private ParticipantController oppositeController;
    int cardNum;

    public PlaySpecialCardCommand(int cardNum , ArenaController arenaController){
        this.arenaController = arenaController;
        this.currentController = arenaController.getCurrent();
        this.oppositeController = arenaController.getOpponent();
        this.cardNum = cardNum - 1;
    }

    public void execute() {
        SpecialCard a = currentController.getParticipant().getPlayDeck().get(cardNum);

        if (a.getCost() <= currentController.getParticipant().getMana()) {
            arenaController.getActivationFactory().getActivation(a)
                .activate(SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        }
    }

}
