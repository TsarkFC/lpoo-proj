import com.googlecode.lanterna.TextCharacter;

import java.util.List;

public class Enemy {
    private List<Card> draw_deck;
    private List<SpecialCard> play_deck;

    public Enemy(List<Card> draw_deck, List<SpecialCard> play_deck){
        this.draw_deck  = draw_deck; //dar shuffle
        this.play_deck = play_deck;
    }

}
