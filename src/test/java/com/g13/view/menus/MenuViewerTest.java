package com.g13.view.menus;

import com.g13.model.menus.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.googlecode.lanterna.input.KeyStroke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;

public class MenuViewerTest {
    @Test
    public void testKeyboardInput() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Level menu = Mockito.mock(Level.class);
        KeyStroke input = Mockito.mock(KeyStroke.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);
        Mockito.doNothing().when(screen).close();

        LevelViewer viewer = new LevelViewer(menu, screen);

        Mockito.when(screen.readInput()).thenReturn(input);
        Mockito.when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertEquals(MenuViewer.COMMAND.DOWN, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.ArrowUp);
        assertEquals(MenuViewer.COMMAND.UP, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Enter);
        assertEquals(MenuViewer.COMMAND.SELECT, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        assertEquals(MenuViewer.COMMAND.DOWN, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(input.getCharacter()).thenReturn('q');
        assertEquals(MenuViewer.COMMAND.QUIT, viewer.getNextCommand());

        Mockito.when(input.getKeyType()).thenReturn(KeyType.Backspace);
        assertEquals(MenuViewer.COMMAND.NOTHING, viewer.getNextCommand());
    }
}
