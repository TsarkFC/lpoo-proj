package com.g13.model;

import java.util.List;

public class GameParticipant {
    protected List<Card> draw_deck;
    protected List<SpecialCard> play_deck;
    protected List<Card> default_draw_deck;

    protected BarSet barSet;

    protected boolean turn_over;

    public GameParticipant(List<SpecialCard> play_deck, BarSet barSet){
        this.play_deck = play_deck;
        this.barSet = barSet;
        this.turn_over = false;
    }

    public void setBoth_draw_decks(List<Card> draw_deck) {
        this.draw_deck = draw_deck;
        this.default_draw_deck = draw_deck;
    }

    public List<Card> getDefault_draw_deck() {
        return default_draw_deck;
    }

    public void setDefault_draw_deck(List<Card> draw_deck) {
        this.default_draw_deck = draw_deck;
    }

    public List<Card> getDraw_deck() {
        return draw_deck;
    }

    public void setDraw_deck(List<Card> draw_deck) {
        this.draw_deck = draw_deck;
    }

    public List<SpecialCard> getPlay_deck() {
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
    public int getMaxMana(){
        return barSet.getMaxMana();
    }

    public int getPoints() {
        return barSet.getPoints();
    }
    public void setPoints(int points) {
        barSet.setPoints(points);
    }
    public int getMax_points() {
        return barSet.getMaxPoints();
    }

    public boolean getTurnOver(){
        return turn_over;
    }
    public void setTurnOver(boolean turn_over){ this.turn_over = turn_over; }
}
