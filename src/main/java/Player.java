import java.util.List;

public class Player extends GameParticipant{
    public Player(List<Card> draw_deck, List<SpecialCard> play_deck, int health, int mana){
        super(draw_deck, play_deck, health, mana);
    }
}
