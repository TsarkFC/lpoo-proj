package com.g13.model;

import com.g13.model.Card;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testConstructor(){ //TODO: Use stub for creating cards and create multiple cards
        List<SpecialCard> play_deck = new ArrayList<>();

        GameParticipant player = new GameParticipant(play_deck, 2, 5, 12, 12, 12);

        assertEquals(play_deck, player.getPlay_deck());
        assertEquals(2, player.getHealth());
        assertEquals(5, player.getMana());
        assertEquals(12, player.getMaxHealth());
        assertEquals(12, player.getMaxMana());
        assertEquals(12, player.getMaxHealth());
    }

    @Test
    public void listSetterTest(){
        List<SpecialCard> play_deck = new ArrayList<>();

        GameParticipant player = new GameParticipant(play_deck, 2, 5, 12, 12, 12);

        List<Card> normal_deck2 = new ArrayList<>();
        Card a = new Card(1);
        normal_deck2.add(a);

        player.setBoth_draw_decks(normal_deck2);
        assertEquals(player.getDefault_draw_deck(), normal_deck2);
        assertEquals(player.getDraw_deck(), normal_deck2);
    }
}
