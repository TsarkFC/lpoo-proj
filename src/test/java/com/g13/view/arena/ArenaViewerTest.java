package com.g13.view.arena;

import com.g13.model.arena.Arena;
import com.g13.view.menus.MenuViewer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

public class ArenaViewerTest {
    @Test
    public void testKeyboardInput() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Arena arena = Mockito.mock(Arena.class);
        KeyStroke input = Mockito.mock(KeyStroke.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        ArenaViewer viewer = new ArenaViewer(arena, screen);

        Mockito.when(screen.readInput()).thenReturn(input);

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('1');
        assertEquals(ArenaViewer.COMMAND.ONE, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('2');
        assertEquals(ArenaViewer.COMMAND.TWO, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('3');
        assertEquals(ArenaViewer.COMMAND.THREE, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('4');
        assertEquals(ArenaViewer.COMMAND.FOUR, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('d');
        assertEquals(ArenaViewer.COMMAND.DRAW, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('q');
        assertEquals(ArenaViewer.COMMAND.QUIT, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Backspace);
        assertEquals(ArenaViewer.COMMAND.NOTHING, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Enter);
        assertEquals(ArenaViewer.COMMAND.SWITCH, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Tab);
        assertEquals(ArenaViewer.COMMAND.PLAYCARD, viewer.getNextCommand());
    }
}
