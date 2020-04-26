package commands;

import controller.ArenaController;
import controller.CardController;
import controller.GameParticipantController;
import model.Arena;
import model.Card;
import model.GameParticipant;

import static java.lang.Integer.min;

public class DrawCardCommand implements Command{
    private ArenaController controller;
    private GameParticipantController current;
    private GameParticipantController opposite;

    public DrawCardCommand(ArenaController controller, GameParticipantController currentController, GameParticipantController oppositeController){
        this.controller = controller;
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

        if(current.getPoints() > current.getMax_points()){
            int a = min(current.getPoints(), 6);
            a = min(a, opposite.getPoints());
            current.setPoints(a);
            //TODO: End turn for both players function
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            current.setTurnOver(true);
            opposite.setTurnOver(true);
        }

        if(current.getPoints() == current.getMax_points()){
            opposite.setTurnOver(true);
        }

        //TODO: Use turn_over variables to check if they player's turn is over (using a command?)
        TurnChecker checker = new TurnChecker(controller);
        checker.execute();
        controller.getModel().notifyObservers();
    }
}
