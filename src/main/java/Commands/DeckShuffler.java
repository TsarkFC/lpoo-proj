package Commands;

import Model.AnyCard;

import java.util.Collections;
import java.util.List;

public class DeckShuffler {

    List<AnyCard> deck;

    public DeckShuffler(List<AnyCard> deck){
        this.deck = deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }
}
