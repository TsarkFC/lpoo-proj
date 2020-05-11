package com.g13.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testConstructor(){ //TODO: Use stub for creating cards and create multiple cards
        List<SpecialCard> play_deck = new ArrayList<>();

        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet = new BarSet(healthBar, manaBar, pointBar);

        GameParticipant player = new GameParticipant(play_deck, barSet);

        assertEquals(play_deck, player.getPlay_deck());
        assertEquals(0, player.getPoints());
        assertEquals(2, player.getHealth());
        assertEquals(5, player.getMana());
        assertEquals(12, player.getMaxMana());
        assertEquals(12, player.getMaxHealth());
        assertEquals(12, player.getMax_points());
        assertEquals(false, player.getTurnOver());
    }

    @Test
    public void listSetterTest(){
        List<SpecialCard> play_deck = new ArrayList<>();

        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet = new BarSet(healthBar, manaBar, pointBar);

        GameParticipant player = new GameParticipant(play_deck, barSet);

        List<Card> normal_deck2 = new ArrayList<>();
        Card a = new Card(1);
        normal_deck2.add(a);

        player.setBoth_draw_decks(normal_deck2);
        assertEquals(player.getDefault_draw_deck(), normal_deck2);
        assertEquals(player.getDraw_deck(), normal_deck2);
    }
}
