package com.g13.view.menus;

import com.g13.model.menus.Start;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;

public class StartViewerTest {
    @Test
    public void levelViewerTest() throws IOException {
        Start start = new Start();
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StartViewer viewer = new StartViewer(start, screen);
        viewer.draw();

        Mockito.verify(graphics, Mockito.times(calculateTitlesSize(start)+2)).putString(anyInt(), anyInt(), anyString());
    }

    @Test
    public void drawInstructionsTest() throws IOException {
        Start start = new Start();
        start.setSelection(-1);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        StartViewer viewer = new StartViewer(start, screen);
        viewer.draw();

        Mockito.verify(graphics, Mockito.times(6 + 23)).putString(anyInt(), anyInt(), anyString());
    }

    private int calculateTitlesSize(Start start){
        int result = 0;
        for (int i = 0; i < start.getTitle_tyrant().length; i++)
            for (int j = 0; j < start.getTitle_tyrant()[i].length; j++)
                result++;
        for (int i = 0; i < start.getTitle_void().length; i++)
            for (int j = 0; j < start.getTitle_void()[i].length; j++)
                result++;
        return  result;
    }
}
