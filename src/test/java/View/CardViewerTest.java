package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Test;
import org.mockito.Mockito;

public class CardViewerTest {
    //DÚVIDA
    /*@Test
    public void testConstructor() {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        CardViewer cardViewer = new CardViewer(screen);

        //Mockito.verify(screen, Mockito.times(1)).newTextGraphics();
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
    }*/
}
