package Model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testConstructor(){ //TODO: Use stub for creating cards and create multiple cards
        List<SpecialCard> play_deck = new ArrayList<>();
        List<Card> normal_deck = new ArrayList<>();

        Player player = new Player(normal_deck, play_deck, 2, 5, 12, 12, 12);

        assertEquals(normal_deck, player.getDraw_deck());
        assertEquals(play_deck, player.getPlay_deck());
        assertEquals(2, player.getHealth());
        assertEquals(5, player.getMana());
        assertEquals(12, player.getMaxHealth());
        assertEquals(12, player.getMaxMana());
        assertEquals(12, player.getMaxHealth());
    }

    @Test
    public void testEmptyDeckController(){
        List<SpecialCard> play_deck = new ArrayList<>();
        List<Card> normal_deck = new ArrayList<>();

        Player player = new Player(normal_deck, play_deck, 2, 5, 12, 12, 12);

        assertEquals(player.getDraw_deck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++){
            a += player.getDraw_deck().get(i).getValue();
        }

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }

    @Test
    public void listSetterTest(){
        List<SpecialCard> play_deck = new ArrayList<>();
        List<Card> normal_deck = new ArrayList<>();

        Player player = new Player(normal_deck, play_deck, 2, 5, 12, 12, 12);

        List<Card> normal_deck2 = new ArrayList<>();
        Card a = new Card(1);
        normal_deck2.add(a);

        player.setBoth_draw_decks(normal_deck2);
        assertEquals(player.getDefault_draw_deck(), normal_deck2);
        assertEquals(player.getDraw_deck(), normal_deck2);
    }
}
