package view;

import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

public class CardViewerTest {
    @Test
    public void testConstructor() {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        new CardViewer(screen);

        Mockito.verify(screen, Mockito.times(1)).newTextGraphics();
    }

    @Test
    public void drawCardTest(){
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);

        CardViewer cardViewer = new CardViewer(screen);


    }
}
