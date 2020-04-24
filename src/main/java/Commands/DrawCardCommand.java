package Commands;

import Controller.CardController;
import Controller.GameParticipantController;
import Model.Arena;
import Model.Card;
import Model.GameParticipant;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class DrawCardCommand implements Command{
    private final Arena arena;
    private GameParticipant part;
    private final GameParticipantController controller;
    public DrawCardCommand(Arena arena, GameParticipant part){
        this.arena = arena;
        this.part = part;
        this.controller = new GameParticipantController(part);
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
