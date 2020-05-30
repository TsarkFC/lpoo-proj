package com.g13.controller.state;

import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;

public class GameStateTest {
    @Test
    public void gameStateTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        StateRecognizer recognizer = new StateRecognizer(screen);
        recognizer.setGameState();
        GameState state = new GameState(recognizer);

        state.advance();

        assertTrue(recognizer.getCurrentState() instanceof GameState);
    }
}
