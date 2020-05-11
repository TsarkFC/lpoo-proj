package com.g13.controller;

import com.g13.controller.commands.DeckShuffler;
import com.g13.model.Card;
import com.g13.model.GameParticipant;

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

        setDraw_deck(defaultDeck);
    }

    public void resetDrawDeck(){
        List<Card> deck = new ArrayList<>();
        deck.addAll(gameParticipant.getDefault_draw_deck());
        DeckShuffler shuffle = new DeckShuffler(deck);
        shuffle.execute();
        setDraw_deck(deck);
    }

    public void removeDeckTop(){
        List<Card> deckCopy = new ArrayList<>();
        deckCopy.addAll(getDraw_deck());
        deckCopy.remove(0);
        setDraw_deck(deckCopy);
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

    public void subtractPoints(int points){
        gameParticipant.setPoints(gameParticipant.getPoints() - points);
    }

    public int getHealth(){ return gameParticipant.getHealth();}
    public void zeroHealth(){
        if (gameParticipant.getPoints() < 0)
            gameParticipant.setHealth(0);
    }
    public void subtractHealth(int value){
        gameParticipant.setHealth(gameParticipant.getHealth() - value);
    }

    public void setTurnOver(boolean value){
        gameParticipant.setTurnOver(value);
    }

    public void setDraw_deck(List<Card> deck){
        gameParticipant.setDraw_deck(deck);
    }
}
