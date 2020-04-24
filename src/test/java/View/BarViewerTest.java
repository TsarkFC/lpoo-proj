package View;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

public class BarViewerTest {
    @Test
    public void testHeaderBar(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(screen);

        barViewer.headerBar(0, 0, graphics, 2, 12);

        Mockito.verify(graphics, Mockito.times(1)).putString(19, 0, " ");
        Mockito.verify(graphics, Mockito.times(1)).putString(20, 0, " ");
        Mockito.verify(graphics, Mockito.times(1)).putString(18, 0, "2");
        Mockito.verify(graphics, Mockito.times(1)).putString(21, 0, "/");
        Mockito.verify(graphics, Mockito.times(1)).putString(22, 0, "12");
    }

    @Test
    public void testEmptyFillBar(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics filled_point = Mockito.mock(TextGraphics.class);
        TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(screen);

        barViewer.fillBar(0, 0, filled_point, unfilled_point, 0, 12);

        Mockito.verify(filled_point, Mockito.times(1)).putString(0, 0, "|");
        for (int i = 4; i <= 16; i += 4)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(i, 0, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(j + j / 3 + 1, 0, "_");
    }

    @Test
    public void testFullFillBar(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics filled_point = Mockito.mock(TextGraphics.class);
        TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(screen);

        barViewer.fillBar(0, 0, filled_point, unfilled_point, 12, 12);

        for (int i = 0; i <= 16; i += 4)
            Mockito.verify(filled_point, Mockito.times(1)).putString(i, 0, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(filled_point, Mockito.times(1)).putString(j + j / 3 + 1, 0, "_");
    }
}
