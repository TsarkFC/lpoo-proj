package com.g13.view.arena;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BarViewerTest {

    TextGraphics unfilled_point = Mockito.mock(TextGraphics.class);
    TextGraphics filled_point = Mockito.mock(TextGraphics.class);
    TextGraphics point_number = Mockito.mock(TextGraphics.class);
    BarViewer barViewer = new BarViewer(unfilled_point, filled_point, point_number);

    @Test
    public void testdrawPointBar(){
        barViewer.drawPointBar(3, 3, 0, 6);

        testEmptyFillBar(unfilled_point, filled_point);

        Mockito.verify(unfilled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#AA8855"));
        Mockito.verify(unfilled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        Mockito.verify(filled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        Mockito.verify(filled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        Mockito.verify(point_number, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#AA8855"));
        Mockito.verify(point_number, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        Mockito.verify(point_number, Mockito.times(1)).
                putString(3 + 19, 3, " ");
        Mockito.verify(point_number, Mockito.times(1)).
                putString(3 + 18, 3, "0");
        Mockito.verify(point_number, Mockito.times(1)).
                putString(3 + 20, 3, "/");
        Mockito.verify(point_number, Mockito.times(1)).
                putString(3 + 21, 3, "6");
    }

    @Test
    public void testFulldrawPointBar(){
        barViewer.drawPointBar(3, 3, 6, 6);

        testFullFillBar(filled_point);

        Mockito.verify(filled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#FFEECC"));
        Mockito.verify(filled_point, Mockito.times(2)).
                setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }

    @Test
    public void testdrawHealthBar(){
        barViewer.drawHealthBar(3, 3, 6, 6);

        testDrawBar(filled_point, point_number, "Hp:");

        Mockito.verify(filled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        Mockito.verify(filled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#AA5555"));

        Mockito.verify(unfilled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#775555"));
        Mockito.verify(unfilled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#777777"));

        Mockito.verify(point_number, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        Mockito.verify(point_number, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    @Test
    public void testdrawManaBar(){
        barViewer.drawManaBar(3, 3, 6, 6);

        testDrawBar(filled_point, point_number, "Mana:");

        Mockito.verify(filled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        Mockito.verify(filled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#5555AA"));

        Mockito.verify(unfilled_point, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#6688AA"));
        Mockito.verify(unfilled_point, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#777777"));

        Mockito.verify(point_number, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        Mockito.verify(point_number, Mockito.times(1)).
                setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    private void testEmptyFillBar(TextGraphics unfilled_point, TextGraphics filled_point){

        Mockito.verify(filled_point, Mockito.times(1)).putString(3, 3, "|");
        for (int i = 4; i <= 16; i += 4)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(3+i, 3, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(unfilled_point, Mockito.times(1)).putString(3 + j + j / 3 + 1, 3, "_");
    }

    private void testFullFillBar(TextGraphics filled_point){
        for (int i = 0; i <= 16; i += 4)
            Mockito.verify(filled_point, Mockito.times(1)).putString(3 + i, 3, "|");

        for (int j = 0; j <= 11; j++)
            Mockito.verify(filled_point, Mockito.times(1)).putString(3 + j + j / 3 + 1, 3, "_");
    }

    private void testHeaderBar(TextGraphics point_number){

        Mockito.verify(point_number, Mockito.times(1)).putString(3+19, 3, " ");
        Mockito.verify(point_number, Mockito.times(1)).putString(3+20, 3, " ");
        Mockito.verify(point_number, Mockito.times(1)).putString(3+18, 3, "6");
        Mockito.verify(point_number, Mockito.times(1)).putString(3+21, 3, "/");
        Mockito.verify(point_number, Mockito.times(1)).putString(3+22, 3, "6");
    }

    private void testDrawBar(TextGraphics filled_point, TextGraphics point_number, String word){
        testFullFillBar(filled_point);
        testHeaderBar(point_number);
        Mockito.verify(point_number, Mockito.times(1)).
                putString(3-word.length(), 3, word);
    }
}
