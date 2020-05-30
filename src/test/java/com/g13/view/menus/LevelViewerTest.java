package com.g13.view.menus;

import com.g13.model.menus.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;

public class LevelViewerTest {
    @Test
    public void levelViewerTest() throws IOException {
        Level level = new Level();
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
        Mockito.when(graphics.setBackgroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.setForegroundColor(any(TextColor.class))).thenReturn(graphics);
        Mockito.when(graphics.putString(anyInt(), anyInt(), anyString())).thenReturn(graphics);

        LevelViewer viewer = new LevelViewer(level, screen);
        viewer.draw();

        Mockito.verify(graphics, Mockito.times(calculateTitlesSize(level)+9)).putString(anyInt(), anyInt(), anyString());
    }

    private int calculateTitlesSize(Level level){
        int result = 0;
        for (int i = 0; i < level.getTitle_tyrant().length; i++)
            for (int j = 0; j < level.getTitle_tyrant()[i].length; j++)
                result++;
        for (int i = 0; i < level.getTitle_void().length; i++)
            for (int j = 0; j < level.getTitle_void()[i].length; j++)
                result++;
        return  result;
    }
}
