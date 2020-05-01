package com.g13.view;

import com.g13.view.CardViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCard;
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
    }

    @Test
    public void testdrawSpecialCard(){
        SpecialCard card = Mockito.mock(SpecialCard.class);
        Mockito.when(card.getCost()).thenReturn(5);
        Mockito.when(card.getSymbol()).thenReturn('*');

        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        viewer.drawSpecialCard(5, card);

        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 16, String.valueOf(card.getCost()));
        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 17, String.valueOf(card.getSymbol()));
    }

    @Test
    public void testdrawCardInfo() throws IOException {
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        GameParticipant player = Mockito.mock(GameParticipant.class);
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        Mockito.when(player.getCardInfo(0)).thenReturn("INFO");
        viewer.drawCardInfo(0, screen, player);

        Mockito.verify(graphics, Mockito.times(1)).
                putString(20, 24, "Card Info:");
        Mockito.verify(graphics, Mockito.times(1)).
                putString(20, 25, "INFO");
        Mockito.verify(screen, Mockito.times(1)).
                refresh();
    }
}
