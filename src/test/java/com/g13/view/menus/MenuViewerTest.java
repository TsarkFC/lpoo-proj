package com.g13.view.menus;

import com.g13.model.menus.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.mockito.Mockito;
import com.googlecode.lanterna.input.KeyStroke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;

public class MenuViewerTest {
    @Property
    public void testKeyboardInput(@ForAll MenuViewer.COMMAND command) throws IOException {
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

        if (command == MenuViewer.COMMAND.DOWN)
            Mockito.when(input.getKeyType()).thenReturn(KeyType.ArrowDown);
        else if (command == MenuViewer.COMMAND.UP)
            Mockito.when(input.getKeyType()).thenReturn(KeyType.ArrowUp);
        else if (command == MenuViewer.COMMAND.SELECT)
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Enter);
        else if (command == MenuViewer.COMMAND.QUIT) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('q');
        }
        else
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Backspace);

        assertEquals(command, viewer.getNextCommand());
    }
}
