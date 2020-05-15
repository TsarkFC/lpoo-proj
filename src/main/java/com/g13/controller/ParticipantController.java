package com.g13.controller;

import com.g13.controller.commands.DeckShuffler;
import com.g13.model.Card;
import com.g13.model.GameParticipant;
import com.g13.model.specialcards.SpecialCard;

import java.util.ArrayList;
import java.util.List;

public class ParticipantController {
    private GameParticipant gameParticipant;

    public ParticipantController(GameParticipant gameParticipant){
        this.gameParticipant = gameParticipant;
    }

    public void setDefaultDeck(){
        List<Card> defaultDeck = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            Card card = new Card((i / 4) + 1);
            defaultDeck.add(card);
        }
        gameParticipant.setDefaultDrawDeck(defaultDeck);

        DeckShuffler deck_shuffler = new DeckShuffler(defaultDeck);
        deck_shuffler.execute();

        setDrawDeck(defaultDeck);
    }

    public void resetDrawDeck(){
        List<Card> deck = new ArrayList<>();
        deck.addAll(gameParticipant.getDefaultDrawDeck());
        DeckShuffler shuffle = new DeckShuffler(deck);
        shuffle.execute();
        setDrawDeck(deck);
    }

    public void removeDeckTop(){
        List<Card> deckCopy = new ArrayList<>();
        deckCopy.addAll(getDrawDeck());
        deckCopy.remove(0);
        setDrawDeck(deckCopy);
    }

    public List<Card> getDrawDeck(){ return gameParticipant.getDrawDeck(); }

    public GameParticipant getParticipant(){
        return gameParticipant;
    }

    public int getPoints(){
        return gameParticipant.getPoints();
    }
    public int getMaxPoints(){
        return gameParticipant.getMaxPoints();
    }
    public void setPoints(int points){ gameParticipant.setPoints(points); }
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


    public void setCardSelected(int cardno, boolean value){
        gameParticipant.getPlayDeck().get(cardno).setSelected(value);
    }
    public void resetCardSelection(){
        for (int i = 0; i<gameParticipant.getPlayDeck().size() && i<4; i++)
            gameParticipant.getPlayDeck().get(i).setSelected(false);
    }

    public void setTurnOver(boolean value){
        gameParticipant.setTurnOver(value);
    }

    public void setDrawDeck(List<Card> deck){ gameParticipant.setDrawDeck(deck); }

    public SpecialCard.CARD_TYPE getCardType(int cardno){
       return gameParticipant.getPlayDeck().get(cardno).getCardType();
    }
}
