package com.g13.controller.arena;

import com.g13.controller.arena.activationfactory.ActivationFactory;
import com.g13.controller.arena.commands.DeckShuffler;
import com.g13.model.arena.Card;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.SpecialCard;

import java.rmi.activation.ActivateFailedException;
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

    public List<Card> getDrawDeck() { return gameParticipant.getDrawDeck(); }
    public List<SpecialCard> getPlayDeck() { return gameParticipant.getPlayDeck(); }

    public GameParticipant getParticipant(){
        return gameParticipant;
    }

    public int getPoints(){
        return gameParticipant.getPoints();
    }
    public int getMaxPoints(){
        return gameParticipant.getMaxPoints();
    }
    public void setPoints(int points) { gameParticipant.setPoints(points); }
    public void subtractPoints(int points){
        gameParticipant.setPoints(gameParticipant.getPoints() - points);
    }

    public int getHealth() { return gameParticipant.getHealth();}
    public void setHealth(int health) {gameParticipant.setHealth(health);}
    public void zeroHealth(){
        if (gameParticipant.getPoints() < 0)
            gameParticipant.setHealth(0);
    }
    public void subtractHealth(int value){
        gameParticipant.setHealth(gameParticipant.getHealth() - value);
    }

    public int getMaxMana() { return gameParticipant.getMaxMana();}

    public int getMana() { return gameParticipant.getMana();}
    public void setMana(int mana) { gameParticipant.setMana(mana); }
    public void subtractMana(int value){
        gameParticipant.setMana(gameParticipant.getMana() - value);
    }

    public void setCardSelected(int cardno){
        resetCardSelection(cardno);
        getPlayDeck().get(cardno).setSelected(!getPlayDeck().get(cardno).getSelected());
    }
    private void resetCardSelection(int cardno){
        for (int i = 0; i<gameParticipant.getPlayDeck().size(); i++) {
            if (i == cardno) continue;
            gameParticipant.getPlayDeck().get(i).setSelected(false);
        }
    }

    public void setTurnOver(boolean value){
        gameParticipant.setTurnOver(value);
    }
    public boolean getTurnOver() {return gameParticipant.getTurnOver(); }

    public void setDrawDeck(List<Card> deck){ gameParticipant.setDrawDeck(deck); }

    public SpecialCard getCard(int cardno){
       return gameParticipant.getPlayDeck().get(cardno);
    }

    public void resetPlayer(){
        setPoints(0);
        setHealth(20);
        setMana(20);
        resetDrawDeck();
        gameParticipant.setActiveCards(new ArrayList<>());
    }

    public void resetOnWin(){
        setMana(20);
        setPoints(0);
        gameParticipant.setActiveCards(new ArrayList<>());
    }

    public int getSelected(){
        for (int i = 0; i < getPlayDeck().size(); i++)
            if (getPlayDeck().get(i).getSelected())
                return i;
        return -1;
    }
}
