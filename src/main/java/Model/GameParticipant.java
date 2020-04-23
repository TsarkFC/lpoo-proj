package Model;

import Commands.DeckShuffler;
import Controller.CardController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GameParticipant {
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
    protected CardController cardController;

    public GameParticipant(List<Card> draw_deck, List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points){
        Collections.shuffle(draw_deck);
        this.draw_deck = draw_deck;
        this.play_deck = play_deck;
        this.health = health;
        this.mana = mana;
        this.max_health = max_health;
        this.max_mana = max_mana;
        this.max_points = max_points;
        this.points = 0;
        this.turn_over = false;

        default_draw_deck = new ArrayList<>();
        if(draw_deck.isEmpty()) {
            for (int i = 0; i <= 23; i++) {
                Card card = new Card((i / 4) + 1);
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

    public void drawCard(){ //-> Boolean verifies if overflow occured
        if(draw_deck.size() == 0){
            //Throw something?
            return;
        }
        Card a = draw_deck.get(0);

        //Bad thing here, SOLID principle broken
        //TODO: Unbreak first SOLID principle

        cardController = new CardController(a);
        cardController.effect(this);
        draw_deck.remove(0);
        if(draw_deck.size() == 0){
            resetDrawDeck();
        }

        /*if(points > max_points){
            points = 6;
            return true;
        }else if (points == max_points) return true;
*/
        return;
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

    public void setPlay_deck(List<SpecialCard> play_deck) {
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
