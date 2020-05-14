package com.g13.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCardTypes.SpecialCard;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CardViewerTest {
    @Test
    public void testdrawCard() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        viewer.drawCard(5,5,10);
        Mockito.verify(graphics, Mockito.times(1)).putString(5+2, 5+1, "10");
        Mockito.verify(graphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        Mockito.verify(graphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 3; j++)
                Mockito.verify(graphics, Mockito.times(1)).putString(5+i, 5+j, " ");
    }

    @Test
    public void testdrawSpecialCard(){
        SpecialCard card = Mockito.mock(SpecialCard.class);
        Mockito.when(card.getCost()).thenReturn(5);
        Mockito.when(card.getSymbol()).thenReturn('*');

        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        viewer.drawSpecialCard(5, card);

        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 3; j++)
                Mockito.verify(graphics, Mockito.times(1)).putString(5+i, 15, " ");

        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 16, String.valueOf(card.getCost()));
        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 17, String.valueOf(card.getSymbol()));
        Mockito.verify(graphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        Mockito.verify(graphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testdrawCardInfo() throws IOException {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        viewer.drawCardInfo("INFO");

        Mockito.verify(graphics, Mockito.times(1)).
                putString(20, 24, "Card Info:");
        Mockito.verify(graphics, Mockito.times(1)).
                putString(1, 25, "INFO");
        Mockito.verify(graphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#336699"));
        Mockito.verify(graphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }
}
