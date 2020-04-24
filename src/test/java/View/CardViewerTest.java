package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

public class CardViewerTest {
    @Test
    public void testConstructor() {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        CardViewer cardViewer = new CardViewer(screen);
        Mockito.verify(screen, Mockito.times(1)).newTextGraphics();
    }

    @Test
    public void testSetGraphicsSettings(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        CardViewer cardViewer = new CardViewer(screen);
        cardViewer.setGraphics(graphics);
        cardViewer.setGraphicsSettings("#EECC88", "#FF0000");
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }
}
