package Model;

import java.util.List;

public class Player extends GameParticipant{
    public Player(List<AnyCard> draw_deck, List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points){
        super(draw_deck, play_deck, health, mana, max_health, max_mana, max_points);
    }

    /*public boolean drawCard(){ //-> Boolean verifies if overflow occured
        if(draw_deck.size() == 0){
            //Throw something?
            return false;
        }
        AnyCard a = draw_deck.get(0);

        //Bad thing here, SOLID principle broken
        //TODO: Unbreak first SOLID principle

        //setPoints(getPoints() + ((Card) a).getValue());
        a.effect(this);
        draw_deck.remove(0);
        if(super.draw_deck.size() == 0){
            resetDrawDeck();
        }

        if(points > max_points){
            points = 6;
            return true;
        }else if (points == max_points) return true;

        return false;
    }*/
}
