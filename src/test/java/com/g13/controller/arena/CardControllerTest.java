package com.g13.controller.arena;

import com.g13.model.arena.Bar;
import com.g13.model.arena.BarSet;
import com.g13.model.arena.Card;
import com.g13.model.arena.GameParticipant;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardControllerTest {

    @Test
    public void testeffect(){
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet = new BarSet(healthBar, manaBar, pointBar);

        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);
        new CardController(new Card(3)).effect(player);
        assertEquals(3, player.getPoints());
    }
}
