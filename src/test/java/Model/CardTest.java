package Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testConstructor(){
        Card card = new Card(5);

        assertEquals(5, card.getValue());
        assertEquals("5", card.getValueString());
    }

    @Test
    public void testEffect() {
        Player player = new Player(new ArrayList<>(), new ArrayList<>(), 12, 0, 12, 12, 12);
        Card card = new Card(5);
        card.effect(player);

        assertEquals(5, player.getPoints());
    }
}
