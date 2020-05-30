package com.g13.view.arena;

import com.g13.model.arena.Arena;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

public class ArenaViewerTest {
    @Property
    public void testKeyboardInput(@ForAll ArenaViewer.COMMAND command) throws IOException {
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

        if (command == ArenaViewer.COMMAND.ONE) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('1');
        }
        else if (command == ArenaViewer.COMMAND.TWO) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('2');
        }
        else if (command == ArenaViewer.COMMAND.THREE) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('3');
        }
        else if (command == ArenaViewer.COMMAND.FOUR) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('4');
        }
        else if (command == ArenaViewer.COMMAND.DRAW) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('d');
        }
        else if (command == ArenaViewer.COMMAND.QUIT) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Character);
            Mockito.when(input.getCharacter()).thenReturn('q');
        }
        else if (command == ArenaViewer.COMMAND.SWITCH) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Enter);
        }
        else if (command == ArenaViewer.COMMAND.PLAYCARD) {
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Tab);
        }
        else{
            Mockito.when(input.getKeyType()).thenReturn(KeyType.Backspace);
        }

        assertEquals(command, viewer.getNextCommand());
    }
}
