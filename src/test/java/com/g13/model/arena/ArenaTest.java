package com.g13.model.arena;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaTest {
    @Test
    public void testConstructor(){
        Arena arena = new Arena(10, 10);

        assertEquals(10, arena.getHeight());
        assertEquals(10, arena.getWidth());
        assertEquals(false, arena.isFinished());
    }

    @Test
    public void testFinish(){
        Arena arena = new Arena(10, 10);
        arena.finish();
        assertEquals(true, arena.isFinished());
    }
}
