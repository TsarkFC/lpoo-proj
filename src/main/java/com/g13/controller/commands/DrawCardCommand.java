package com.g13.controller.commands;

import com.g13.controller.ArenaController;
import com.g13.controller.CardController;
import com.g13.controller.ParticipantController;
import com.g13.model.Card;

import static java.lang.Integer.min;

public class DrawCardCommand implements Command{
    private ParticipantController current;
    private ParticipantController opposite;
    private ArenaController arenaController;

    public DrawCardCommand(ArenaController arena, ParticipantController currentController, ParticipantController oppositeController){
        this.arenaController = arena;
        this.current = currentController;
        this.opposite = oppositeController;
    }

    @Override
    public void execute() {
        Card card = current.getDraw_deck().get(0);
        CardController c = new CardController(card);
        c.effect(current.getParticipant());

        current.removeDeckTop();

        if(current.getDraw_deck().size() == 0)
            current.resetDrawDeck();

        /*if(current.getPoints() > current.getMax_points()){
            int a = min(current.getPoints() - 1, 6);
            a = min(a, opposite.getPoints() - 1);
            if(a < 0)
                a = 0;
            current.setPoints(a);
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            current.setTurnOver(true);
            opposite.setTurnOver(true);
        }*/
        arenaController.checkControllerPoints(current, opposite);

        if(current.getPoints() == current.getMax_points()){
            current.setTurnOver(true);
        }
        if(opposite.getPoints() == opposite.getMax_points()){
            opposite.setTurnOver(true);
        }
    }
}
