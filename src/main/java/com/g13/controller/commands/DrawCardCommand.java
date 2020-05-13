package com.g13.controller.commands;

import com.g13.controller.ArenaController;
import com.g13.controller.CardController;
import com.g13.controller.ParticipantController;
import com.g13.model.Card;

public class DrawCardCommand implements Command{
    private ArenaController arenaController;

    public DrawCardCommand(ArenaController arena){
        this.arenaController = arena;
    }

    @Override
    public void execute() {
        ParticipantController current = arenaController.getCurrent();
        ParticipantController opposite = arenaController.getOpponent();

        Card card = current.getDraw_deck().get(0);
        CardController c = new CardController(card);
        c.effect(current.getParticipant());

        current.removeDeckTop();

        if(current.getDraw_deck().size() == 0)
            current.resetDrawDeck();

        arenaController.checkControllerPoints();

        if(current.getPoints() == current.getMax_points())
            current.setTurnOver(true);
        if(opposite.getPoints() == opposite.getMax_points())
            opposite.setTurnOver(true);
    }
}
