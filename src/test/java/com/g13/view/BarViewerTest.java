package com.g13.view;

import com.g13.view.BarViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.Test;
import org.mockito.Mockito;

public class BarViewerTest {
    @Test
    public void testHeaderBar(){
        TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);
        TextGraphics filled_point = Mockito.mock(TextGraphics.class);
        TextGraphics point_number = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(unfilled_point, filled_point, point_number);

        barViewer.headerBar(0, 0, 2, 12);

        Mockito.verify(point_number, Mockito.times(1)).putString(19, 0, " ");
        Mockito.verify(point_number, Mockito.times(1)).putString(20, 0, " ");
        Mockito.verify(point_number, Mockito.times(1)).putString(18, 0, "2");
        Mockito.verify(point_number, Mockito.times(1)).putString(21, 0, "/");
        Mockito.verify(point_number, Mockito.times(1)).putString(22, 0, "12");
    }

    @Test
    public void testEmptyFillBar(){
        TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);
        TextGraphics filled_point = Mockito.mock(TextGraphics.class);
        TextGraphics point_number = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(unfilled_point, filled_point, point_number);

        barViewer.fillBar(0, 0, 0, 12);

        Mockito.verify(filled_point, Mockito.times(1)).putString(0, 0, "|");
        for (int i = 4; i <= 16; i += 4)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(i, 0, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(j + j / 3 + 1, 0, "_");
    }

    @Test
    public void testFullFillBar(){
        TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);
        TextGraphics filled_point = Mockito.mock(TextGraphics.class);
        TextGraphics point_number = Mockito.mock(TextGraphics.class);

        BarViewer barViewer = new BarViewer(unfilled_point, filled_point, point_number);

        barViewer.fillBar(0, 0, 12, 12);

        for (int i = 0; i <= 16; i += 4)
            Mockito.verify(filled_point, Mockito.times(1)).putString(i, 0, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(filled_point, Mockito.times(1)).putString(j + j / 3 + 1, 0, "_");
    }
}
