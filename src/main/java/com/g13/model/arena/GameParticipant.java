package com.g13.model.arena;

import com.g13.model.arena.specialcards.SpecialCard;

import java.util.ArrayList;
import java.util.List;

public class GameParticipant {
    protected List<Card> draw_deck;
    protected List<SpecialCard> play_deck;
    protected List<Card> default_draw_deck;

    protected BarSet barSet;

    protected boolean turn_over;

    protected List<SpecialCard> activeCards;

    public GameParticipant(List<SpecialCard> play_deck, BarSet barSet){
        this.play_deck = play_deck;
        this.barSet = barSet;
        this.turn_over = false;
        this.activeCards = new ArrayList<>();
    }

    public void setBothDrawDecks(List<Card> draw_deck) {
        this.draw_deck = draw_deck;
        this.default_draw_deck = draw_deck;
    }

    public List<Card> getDefaultDrawDeck() {
        return default_draw_deck;
    }

    public void setDefaultDrawDeck(List<Card> draw_deck) {
        this.default_draw_deck = draw_deck;
    }

    public List<Card> getDrawDeck() {
        return draw_deck;
    }

    public void setDrawDeck(List<Card> draw_deck) {
        this.draw_deck = draw_deck;
    }

    public List<SpecialCard> getPlayDeck() {
        return play_deck;
    }

    public String getCardInfo(int i){ return play_deck.get(i).getCardInfo(); }

    public int getHealth() {
        return barSet.getHealth();
    }
    public void setHealth(int health) {barSet.setHealth(health);}
    public int getMaxHealth(){
        return barSet.getMaxHealth();
    }

    public int getMana() {
        return barSet.getMana();
    }
    public void setMana(int mana) { barSet.setMana(mana);}
    public int getMaxMana(){
        return barSet.getMaxMana();
    }

    public int getPoints() {
        return barSet.getPoints();
    }
    public void setPoints(int points) { barSet.setPoints(points); }
    public int getMaxPoints() {
        return barSet.getMaxPoints();
    }

    public boolean getTurnOver(){
        return turn_over;
    }
    public void setTurnOver(boolean turn_over){ this.turn_over = turn_over; }

    public List<SpecialCard> getActiveCards(){return activeCards;}
    public void setActiveCards(List<SpecialCard> activeCards){
        this.activeCards = activeCards;
    }
}
