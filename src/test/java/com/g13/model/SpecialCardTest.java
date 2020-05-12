package com.g13.model;

import com.g13.model.SpecialCardTypes.SpecialCard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialCardTest {
    @Test
    public void testConstructor(){
        SpecialCard card = new SpecialCard(5, '+', "Testing card info");

        assertEquals(5, card.getCost());
        assertEquals('+', card.getSymbol());
        assertEquals("Testing card info", card.getCardInfo());
    }
}
