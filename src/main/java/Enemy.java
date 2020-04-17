import com.googlecode.lanterna.TextCharacter;

import java.util.List;

public class Enemy extends GameParticipant{

    public Enemy(List<Card> draw_deck, List<SpecialCard> play_deck, int health, int mana){
        super(draw_deck, play_deck, health, mana);
    }

}
