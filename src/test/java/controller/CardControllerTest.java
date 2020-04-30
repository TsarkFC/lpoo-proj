package controller;

import model.Card;
import model.GameParticipant;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardControllerTest {

    @Test
    public void testeffect(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), 2, 5, 12, 12, 12);
        new CardController(new Card(3)).effect(player);
        assertEquals(3, player.getPoints());
    }
}
