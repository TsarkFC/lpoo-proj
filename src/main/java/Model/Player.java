package Model;

import java.util.List;

public class Player extends GameParticipant{
    public Player(List<AnyCard> draw_deck, List<AnyCard> play_deck, int health, int mana, int max_health, int max_mana){
        super(draw_deck, play_deck, health, mana, max_health, max_mana);
    }
}
