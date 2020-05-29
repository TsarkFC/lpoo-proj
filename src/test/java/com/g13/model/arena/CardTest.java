package com.g13.model.arena;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    @Test
    public void testConstructor(){
        Card card = new Card(5);

        assertEquals(5, card.getValue());
        assertEquals("5", card.getValueString());
    }
}
