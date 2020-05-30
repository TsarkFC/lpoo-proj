package com.g13.controller.state;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.strategies.AggressivePlayStrategy;
import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.controller.state.statefactory.GameStateFactory;
import static org.mockito.ArgumentMatchers.*;

import com.g13.model.arena.Arena;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;

public class GameStateTest {
    @Test
    public void gameStateTest() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StateRecognizer recognizer = new StateRecognizer(screen);
        recognizer.setGameState(new NormalPlayStrategy());
        GameStateFactory gameStateFactory = new GameStateFactory(recognizer);
        GameState state = new GameState(recognizer, gameStateFactory);
        state.advance();

        assertTrue(recognizer.getCurrentState() instanceof GameState);

        gameStateFactory.getArena().getEnemy().setHealth(0);
        state.advance();

        assertTrue(recognizer.getCurrentState() instanceof LevelState);

        recognizer.setGameState(new NormalPlayStrategy());
        gameStateFactory.getArena().getEnemy().setHealth(1);
        gameStateFactory.getArena().getPlayer().setHealth(0);
        state.advance();

        assertTrue(recognizer.getCurrentState() instanceof LevelState);
    }

    @Test
    public void setStrategyTest(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StateRecognizer recognizer = new StateRecognizer(screen);
        GameStateFactory gameStateFactory = new GameStateFactory(recognizer);
        GameState state = new GameState(recognizer, gameStateFactory);
        assertTrue(state.getModel() instanceof Arena);
        assertTrue(state.getController() instanceof ArenaController);

        assertTrue(!(gameStateFactory.getArena().getEnemy().getPlayStrategy() instanceof AggressivePlayStrategy));

        state.setStrategy(new AggressivePlayStrategy());

        assertTrue(gameStateFactory.getArena().getEnemy().getPlayStrategy() instanceof AggressivePlayStrategy);

    }
}
