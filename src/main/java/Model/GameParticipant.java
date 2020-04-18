package Model;

import Commands.DeckShuffler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameParticipant {
    protected List<AnyCard> draw_deck;
    private List<AnyCard> play_deck;
    private int health;
    private int mana;
    private int max_health;
    private int max_mana;
    private int playingValue; //Value to which basic cards' value is added to (cannot think of a better name)

    private List<AnyCard> default_draw_deck;

    protected int points;
    protected int max_points;

    public GameParticipant(List<AnyCard> draw_deck, List<AnyCard> play_deck, int health, int mana, int max_health, int max_mana){
        Collections.shuffle(draw_deck);
        this.draw_deck = draw_deck;
        this.play_deck = play_deck;
        this.health = health;
        this.mana = mana;
        this.max_health = max_health;
        this.max_mana = max_mana;
        this.playingValue = 0;


        default_draw_deck = new ArrayList<>();
        if(draw_deck.isEmpty()) {
            for (int i = 1; i <= 24; i++) {
                Card card = new Card(i / 4);
                default_draw_deck.add(card);
            }
            draw_deck.addAll(default_draw_deck);
        }
        else{
            this.default_draw_deck.addAll(draw_deck);
        }
        DeckShuffler deck_shuffler = new DeckShuffler(draw_deck);
        deck_shuffler.execute();
    }

    public List<AnyCard> getDraw_deck() {
        return draw_deck;
    }

    public void setDraw_deck(List<AnyCard> draw_deck) {
        this.draw_deck = draw_deck;
    }

    public List<AnyCard> getPlay_deck() {
        return play_deck;
    }

    public void setPlay_deck(List<AnyCard> play_deck) {
        this.play_deck = play_deck;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth(){
        return max_health;
    }

    public void setMaxHealth(int max_health){
        this.max_health = max_health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana(){
        return max_mana;
    }

    public void setMaxMana(int max_mana){
        this.max_mana = max_mana;
    }

    public int getPlayingValue() {
        return playingValue;
    }

    public void setPlayingValue(int playingValue) {
        this.playingValue = playingValue;
    }

    public int getDeckSize(){
        return draw_deck.size();
    }

    public void resetDrawDeck(){
        draw_deck.addAll(default_draw_deck);
        DeckShuffler shuffle = new DeckShuffler(draw_deck);
        shuffle.execute();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
