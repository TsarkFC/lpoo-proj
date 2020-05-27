package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.model.arena.specialcards.SpecialCard;

import java.util.Collections;
import java.util.List;

public class PlaySpecialCardCommand {
    private ArenaController arenaController;
    private ParticipantController currentController;
    private ParticipantController oppositeController;

    public PlaySpecialCardCommand(ArenaController arenaController){
        this.arenaController = arenaController;
        this.currentController = arenaController.getCurrent();
        this.oppositeController = arenaController.getOpponent();
    }

    public void execute() {
        int cardNum = currentController.getSelected();
        SpecialCard a = currentController.getParticipant().getPlayDeck().get(cardNum);
        currentController.setCardSelected(cardNum);

        if (a.getCost() <= currentController.getParticipant().getMana()) {
            arenaController.getActivationFactory().getActivation(a).activate(arenaController);
            List<SpecialCard> cards = currentController.getParticipant().getPlayDeck();
            Collections.swap(cards, cardNum, cards.size() - 1);
        }
    }
}
