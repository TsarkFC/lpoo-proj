package com.g13.view.arena;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.g13.model.arena.specialcards.SpecialCard;
import org.mockito.Mockito;

import org.junit.jupiter.api.Test;

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
        SpecialCard card = new SpecialCard(10, 's', "info");

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
    public void testdrawSelectedSpecialCard(){
        SpecialCard card = new SpecialCard(10, 's', "info");
        card.setSelected(true);

        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        CardViewer viewer = new CardViewer(graphics);

        viewer.drawSpecialCard(5, card);

        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 3; j++)
                Mockito.verify(graphics, Mockito.times(1)).putString(5+i, 15, " ");

        //Private method drawCardInfo()
        Mockito.verify(graphics, Mockito.times(1)).
                putString(20, 24, "Card Info:");
        Mockito.verify(graphics, Mockito.times(1)).
                putString(1, 25, card.getCardInfo());
        Mockito.verify(graphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#336699"));
        Mockito.verify(graphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 15, String.valueOf(card.getCost()));
        Mockito.verify(graphics, Mockito.times(1)).
                putString(7, 16, String.valueOf(card.getSymbol()));
        Mockito.verify(graphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#AA8855"));
        Mockito.verify(graphics, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }
}
