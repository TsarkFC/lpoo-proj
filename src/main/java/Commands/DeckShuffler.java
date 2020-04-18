package Commands;

import Model.AnyCard;

import java.util.Collections;
import java.util.List;

public class DeckShuffler implements Command{

    List<AnyCard> deck;

    public DeckShuffler(List<AnyCard> deck){
        this.deck = deck;
    }

    public void execute(){
        Collections.shuffle(deck);
    }
}
