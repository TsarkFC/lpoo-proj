package com.g13.controller.commands;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.SpecialCardTypes.SpecialCard;

public class PlaySpecialCardCommand {
    private ArenaController arenaController;
    private ParticipantController currentController;
    private ParticipantController oppositeController;
    int cardNum;

    public PlaySpecialCardCommand(int cardNum , ArenaController arenaController, ParticipantController currentController, ParticipantController oppositeController){
        this.arenaController = arenaController;
        this.currentController = currentController;
        this.oppositeController = oppositeController;
        this.cardNum = cardNum - 1;
    }

    public void execute() {
        SpecialCard a = currentController.getParticipant().getPlayDeck().get(cardNum);

        if (a.getCost() <= currentController.getParticipant().getMana()) {
            a.activate(SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY, arenaController, currentController, oppositeController);
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - a.getCost());
        }
    }

}
