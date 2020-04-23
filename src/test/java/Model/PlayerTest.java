package Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testConstructor(){ //TODO: Use stub for creating cards and create multiple cards
        List<SpecialCard> play_deck = new ArrayList<>();
        List<AnyCard> normal_deck = new ArrayList<>();

        Player player = new Player(normal_deck, play_deck, 2, 5, 12, 12, 12);

        assertEquals(normal_deck, player.getDraw_deck());
        assertEquals(play_deck, player.getPlay_deck());
        assertEquals(2, player.getHealth());
        assertEquals(5, player.getMana());
        assertEquals(12, player.getMaxHealth());
        assertEquals(12, player.getMaxMana());
        assertEquals(12, player.getMaxHealth());
    }
}
