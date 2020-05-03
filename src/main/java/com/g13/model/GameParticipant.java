package com.g13.model;

import java.util.List;

public class GameParticipant {
    protected List<Card> draw_deck;
    private List<SpecialCard> play_deck;
    private int health;
    private int mana;
    private int max_health;
    private int max_mana;

    private List<Card> default_draw_deck;

    protected int points;
    protected int max_points;

    protected boolean turn_over;

    //TODO: Simplify constructor -> Play deck can be removed once working on special cards effects
    public GameParticipant(List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points){
        this.play_deck = play_deck;
        this.health = health;
        this.mana = mana;
        this.max_health = max_health;
        this.max_mana = max_mana;
        this.max_points = max_points;
        this.points = 0;
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
        return health;
    }

    public int getMaxHealth(){
        return max_health;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana(){
        return max_mana;
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMax_points() {
        return max_points;
    }

    public boolean getTurnOver(){
        return turn_over;
    }
    public void setTurnOver(boolean turn_over){
        this.turn_over = turn_over;
    }
}
