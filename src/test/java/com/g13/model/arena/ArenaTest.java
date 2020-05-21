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
        assertEquals(new ArrayList<>().size(), arena.getObservers().size());
    }

    @Test
    public void testFinish(){
        Arena arena = new Arena(10, 10);
        arena.finish();
        assertEquals(true, arena.isFinished());
    }

    @Test
    public void testObservers(){
        Arena arena = new Arena(10, 10);
        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        ArenaViewer copy = Mockito.mock(ArenaViewer.class);

        arena.addObserver(arenaViewer);
        arena.addObserver(copy);

        assertEquals(2, arena.getObservers().size());
    }
}
