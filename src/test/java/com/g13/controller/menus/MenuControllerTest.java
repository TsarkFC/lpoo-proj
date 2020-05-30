package com.g13.controller.menus;

import com.g13.controller.state.State;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.view.menus.LevelViewer;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static com.g13.view.menus.MenuViewer.COMMAND.DOWN;
import static com.g13.view.menus.MenuViewer.COMMAND.UP;
import static com.g13.view.menus.MenuViewer.COMMAND.QUIT;
import static com.g13.view.menus.MenuViewer.COMMAND.SELECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuControllerTest {

    @Test
    public void testStart() throws IOException {
        Level level = new Level();
        LevelViewer viewer = Mockito.mock(LevelViewer.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        LevelController controller = new LevelController(level, viewer, recognizer);

        int previous = level.getCross();
        Mockito.when(viewer.getNextCommand()).thenReturn(DOWN);
        controller.start();
        assertEquals(previous + 1, level.getCross());

        previous = level.getCross();
        Mockito.when(viewer.getNextCommand()).thenReturn(UP);
        controller.start();
        assertEquals(previous - 1, level.getCross());

        Mockito.when(viewer.getNextCommand()).thenReturn(QUIT);
        controller.start();
        assertTrue(level.isFinished());

        State state = Mockito.mock(State.class);
        Mockito.when(recognizer.getCurrentState()).thenReturn(state);
        Mockito.doNothing().when(state).advance();
        Mockito.when(viewer.getNextCommand()).thenReturn(SELECT);
        controller.start();
        Mockito.verify(state, Mockito.times(1)).advance();
    }
}
