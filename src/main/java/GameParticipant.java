import java.util.Collections;
import java.util.List;

public class GameParticipant {
    private List<Card> draw_deck;
    private List<SpecialCard> play_deck;
    private int health;
    private int mana;
    private int playingValue; //Value to which basic cards' value is added to (cannot think of a better name)

    public GameParticipant(List<Card> draw_deck, List<SpecialCard> play_deck, int health, int mana){
        Collections.shuffle(draw_deck);
        this.draw_deck = draw_deck;
        this.play_deck = play_deck;
        this.health = health;
        this.mana = mana;
        this.playingValue = 0;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPlayingValue() {
        return playingValue;
    }

    public void setPlayingValue(int playingValue) {
        this.playingValue = playingValue;
    }
}
