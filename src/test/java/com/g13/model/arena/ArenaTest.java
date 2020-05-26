package com.g13.model.arena;

import com.g13.view.arena.ArenaViewer;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
