package Controller;

import Commands.DeckShuffler;
import Model.Card;
import Model.GameParticipant;

import java.util.ArrayList;
import java.util.List;

public class GameParticipantController {
    private GameParticipant gameParticipant;

    public GameParticipantController(GameParticipant gameParticipant){
        this.gameParticipant = gameParticipant;
    }

    public void setDefaultDeck(){
        List<Card> defaultDeck = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            Card card = new Card((i / 4) + 1);
            defaultDeck.add(card);
        }
        gameParticipant.setDefault_draw_deck(defaultDeck);

        DeckShuffler deck_shuffler = new DeckShuffler(defaultDeck);
        deck_shuffler.execute();

        gameParticipant.setDraw_deck(defaultDeck);
    }

    public void resetDrawDeck(){
        List<Card> deck = new ArrayList<>();
        deck.addAll(gameParticipant.getDefault_draw_deck());
        DeckShuffler shuffle = new DeckShuffler(deck);
        shuffle.execute();
        gameParticipant.setDraw_deck(deck);
    }

    public void removeDeckTop(){
        List<Card> deckCopy = new ArrayList<>();
        deckCopy.addAll(gameParticipant.getDraw_deck());
        deckCopy.remove(0);
        gameParticipant.setDraw_deck(deckCopy);
    }
}
