package com.g13.controller.state;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.g13.controller.menus.LevelController;
import com.g13.controller.state.statefactory.LevelStateFactory;
import com.g13.model.menus.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;

public class LevelStateTest {

    @Test
    public void levelStateTest() {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StateRecognizer recognizer = new StateRecognizer(screen);
        LevelStateFactory levelStateFactory = new LevelStateFactory(recognizer);
        LevelState state = new LevelState(recognizer, levelStateFactory);
        assertTrue(state.getController() instanceof LevelController);

        state.advance();
        assertTrue(recognizer.getCurrentState() instanceof StartState);

        levelStateFactory.getLevelController().moveDown();
        state.advance();
        assertTrue(recognizer.getCurrentState() instanceof GameState);

        for (int i = 0; i < 2; i++) {
            recognizer.setLevelState();
            levelStateFactory.getLevelController().unlockNextStage();
            levelStateFactory.getLevelController().moveDown();
            state.advance();
            assertTrue(recognizer.getCurrentState() instanceof GameState);
        }
    }
}
