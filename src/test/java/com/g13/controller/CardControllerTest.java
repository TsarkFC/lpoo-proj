package com.g13.controller;

import com.g13.model.Bar;
import com.g13.model.BarSet;
import com.g13.model.Card;
import com.g13.model.GameParticipant;
import org.junit.Test;
import org.mockito.Mockito;

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
