package model;

import org.junit.Test;
import org.mockito.Mockito;
import view.Gui;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ArenaTest {
    @Test
    public void testConstructor(){
        Arena arena = new Arena(10, 10);

        assertEquals(10, arena.getHeight());
        assertEquals(10, arena.getWidth());
        assertEquals(true, arena.getCurrent());
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
        Gui gui = Mockito.mock(Gui.class);
        Gui copy = Mockito.mock(Gui.class);

        arena.addObserver(gui);
        arena.addObserver(copy);

        assertEquals(2, arena.getObservers().size());
    }

    @Test
    public void testCurrent(){
        Arena arena = new Arena (10, 10);
        arena.setCurrent(false);
        assertEquals(false, arena.getCurrent());
    }
}
