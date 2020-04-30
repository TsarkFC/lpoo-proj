package commands;

import controller.ArenaController;
import model.Card;

import java.util.Collections;
import java.util.List;

public class DeckShuffler implements Command{

    List<Card> deck;

    public DeckShuffler(List<Card> deck){
        this.deck = deck;
    }

    public void execute(){
        Collections.shuffle(deck);
    }
}
