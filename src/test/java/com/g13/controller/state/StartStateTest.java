package com.g13.controller.state;

import com.g13.controller.menus.StartController;
import com.g13.controller.state.statefactory.StartStateFactory;
import com.g13.model.menus.Start;
import com.g13.view.menus.StartViewer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;

public class StartStateTest {
    @Test
    public void startStateTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StateRecognizer recognizer = new StateRecognizer(screen);
        StartStateFactory startStateFactory = new StartStateFactory(recognizer);
        StartState state = new StartState(recognizer, startStateFactory);
        assertTrue(state.getModel() instanceof Start);
        assertTrue(state.getView() instanceof StartViewer);
        assertTrue(state.getController() instanceof StartController);

        state.advance();
        assertTrue(recognizer.getCurrentState() instanceof LevelState);

        recognizer.setStartState();
        startStateFactory.getStart().setSelection(1);
        state.advance();
        assertEquals(-1, startStateFactory.getStart().getSelection());

        recognizer.setStartState();
        startStateFactory.getStart().setSelection(-1);
        state.advance();
        assertEquals(1, startStateFactory.getStart().getSelection());
    }
}
