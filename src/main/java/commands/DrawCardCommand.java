package commands;

import controller.CardController;
import controller.GameParticipantController;
import model.Arena;
import model.Card;
import model.GameParticipant;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class DrawCardCommand implements Command{
    private Arena arena;
    private GameParticipant part;
    private GameParticipantController controller;
    public DrawCardCommand(Arena arena, GameParticipant controller){
        this.arena = arena;
        this.controller = new GameParticipantController(controller);
        this.part = controller;
    }

    @Override
    public void execute() {
        Card card = part.getDraw_deck().get(0);
        CardController c = new CardController(card);
        c.effect(part);

        controller.removeDeckTop();

        if(part.getDraw_deck().size() == 0)
            controller.resetDrawDeck();

        if(part.getPoints() > part.getMax_points()){
            int a = min(arena.getPlayer().getPoints(), 6);
            a = min(a, arena.getEnemy().getPoints());
            part.setPoints(a);
            //TODO: End turn for both players function
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            arena.getPlayer().setTurnOver(true);
            arena.getEnemy().setTurnOver(true);
        }

        if(part.getPoints() == part.getMax_points()){
            part.setTurnOver(true);
        }

        //TODO: Use turn_over variables to check if they player's turn is over (using a command?)
        TurnChecker checker = new TurnChecker(arena);
        checker.execute();
        arena.notifyObservers();
    }
}
