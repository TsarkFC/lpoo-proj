package Model;

import java.util.List;

public class Player extends GameParticipant{
    public Player(List<AnyCard> draw_deck, List<AnyCard> play_deck, int health, int mana, int max_health, int max_mana){
        super(draw_deck, play_deck, health, mana, max_health, max_mana);
    }

    public void drawCard(){
        if(super.draw_deck.size() == 0){
            //Throw something?
            return;
        }
        AnyCard a = super.draw_deck.get(0);

        //Bad thing here, SOLID principle broken
        //TODO: Unbreak first SOLID principle

        //setPoints(getPoints() + ((Card) a).getValue());
        a.effect(this);
        super.draw_deck.remove(0);
        if(super.draw_deck.size() == 0){
            resetDrawDeck();
        }

        /*if(points >= max_points){
            endTurn();
        }*/

        return;
    }
}
