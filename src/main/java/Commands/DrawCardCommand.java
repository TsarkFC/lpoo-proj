package Commands;

import Controller.CardController;
import Model.Arena;
import Model.Card;
import Model.GameParticipant;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class DrawCardCommand implements Command{
    private final Arena arena;
    private final GameParticipant part;
    public DrawCardCommand(Arena arena, GameParticipant part){
        this.arena = arena;
        this.part = part;
    }

    @Override
    public void execute() {


        Card card = part.getDraw_deck().get(0);

        //Bad thing here, SOLID principle broken
        //TODO: Unbreak first SOLID principle


        CardController c = new CardController(card);
        c.effect(part);

        List<Card> deckCopy = new ArrayList<Card>();
        deckCopy.addAll(part.getDraw_deck());
        deckCopy.remove(0);
        part.setDraw_deck(deckCopy);

        if(part.getDraw_deck().size() == 0){
            part.resetDrawDeck();
        }




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
