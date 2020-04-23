package Model;

import java.util.List;

public class Enemy extends GameParticipant{

    public Enemy(List<Card> draw_deck, List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points){
        super(draw_deck, play_deck, health, mana, max_health, max_mana, max_points);
    }


}
