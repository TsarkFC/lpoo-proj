package com.g13.model.arena;

import com.g13.model.arena.specialcards.SpecialCard;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void testConstructor(){
        List<SpecialCard> play_deck = new ArrayList<>();
        GameParticipant player = new GameParticipant(play_deck, initBarSet());
        player.setMana(10);
        player.setHealth(5);

        assertEquals(play_deck, player.getPlayDeck());
        assertEquals(0, player.getPoints());
        assertEquals(5, player.getHealth());
        assertEquals(10, player.getMana());
        assertEquals(12, player.getMaxMana());
        assertEquals(12, player.getMaxHealth());
        assertEquals(12, player.getMaxPoints());
        assertEquals(false, player.getTurnOver());
    }

    @Test
    public void listSetterTest(){
        List<SpecialCard> play_deck = new ArrayList<>();
        GameParticipant player = new GameParticipant(play_deck, initBarSet());

        List<Card> normal_deck2 = new ArrayList<>();
        Card a = new Card(1);
        normal_deck2.add(a);

        player.setBothDrawDecks(normal_deck2);
        assertEquals(player.getDefaultDrawDeck(), normal_deck2);
        assertEquals(player.getDrawDeck(), normal_deck2);

        player.setActiveCards(new ArrayList<>());
        assertEquals(player.getActiveCards().size(), 0);
    }

    private BarSet initBarSet(){
        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }
}
