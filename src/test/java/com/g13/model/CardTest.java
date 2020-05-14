package com.g13.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void testConstructor(){
        Card card = new Card(5);

        assertEquals(5, card.getValue());
        assertEquals("5", card.getValueString());
    }
}
