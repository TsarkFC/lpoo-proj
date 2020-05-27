package com.g13.model.arena;

import com.g13.model.arena.specialcards.SpecialCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialCardTest {
    @Test
    public void testConstructor(){
        SpecialCard card = new SpecialCard(5, '+', "Testing card info");

        assertEquals(5, card.getCost());
        assertEquals('+', card.getSymbol());
        assertEquals("Testing card info", card.getCardInfo());
    }
}
