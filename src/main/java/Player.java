import java.util.List;

public class Player {
    private List<Card> draw_deck;
    private List<SpecialCard> play_deck;

    public Player(List<Card> draw_deck, List<SpecialCard> play_deck){
        this.draw_deck  = draw_deck; //dar shuffle
        this.play_deck = play_deck;
    }
}
