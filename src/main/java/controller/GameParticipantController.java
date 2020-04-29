package controller;

import commands.DeckShuffler;
import model.Card;
import model.GameParticipant;

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

    public List<Card> getDraw_deck(){
        return gameParticipant.getDraw_deck();
    }

    public GameParticipant getParticipant(){
        return gameParticipant;
    }

    public int getPoints(){
        return gameParticipant.getPoints();
    }

    public int getMax_points(){
        return gameParticipant.getMax_points();
    }

    public void setPoints(int points){
        gameParticipant.setPoints(points);
    }

    public void setTurnOver(boolean value){
        gameParticipant.setTurnOver(value);
    }
}
