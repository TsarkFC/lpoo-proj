package com.g13.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BarViewer {
    private TextGraphics unfilled_point;
    private TextGraphics filled_point;
    private TextGraphics pointNumber;

    public BarViewer(TextGraphics un, TextGraphics fill, TextGraphics point){
        unfilled_point = un;
        filled_point = fill;
        pointNumber = point;
    }

    private void setGraphicsValues(TextGraphics graphics, String fore, String back){
        graphics.setBackgroundColor(TextColor.Factory.fromString(back));
        graphics.setForegroundColor(TextColor.Factory.fromString(fore));
    }

    private void setAllGraphicsValues(String fBack, String fFore, String uBack, String uFore, String pBack, String pFore){
        setGraphicsValues(unfilled_point, uFore, uBack);
        setGraphicsValues(filled_point, fFore, fBack);
        setGraphicsValues(pointNumber, pFore, pBack);
    }

    public void drawPointBar(int x, int y, int point_val, int max_point_val){
        setAllGraphicsValues("#EECC88", "#FF0000", "#AA8855", "#FF0000", "#AA8855", "#FFFFFF");

        if(point_val == max_point_val)
            setGraphicsValues(filled_point,"#FF0000", "#FFEECC");

        fillBar(x, y, point_val, max_point_val);

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(point_val));
        pointNumber.putString(x + 20, y, "/");
        pointNumber.putString(x + 21, y, String.valueOf(max_point_val));
    }

    public void drawHealthBar(int x, int y, int health, int max_health){
        setAllGraphicsValues("#BB1111", "#AA5555", "#775555", "#777777", "#BB1111", "#FFFFFF");
        drawBar(x,y,health,max_health,"Hp:");
    }

    public void drawManaBar(int x, int y, int mana, int max_mana){
        setAllGraphicsValues("#3D84CC", "#5555AA", "#6688AA", "#777777", "#3D84CC", "#FFFFFF");
        drawBar(x,y,mana,max_mana,"Mana:");
    }

    private void drawBar(int x, int y, int value, int max_value, String word){
        fillBar(x, y, value, max_value);
        headerBar(x, y, value, max_value);
        pointNumber.putString(x - word.length(), y, word);
    }

    public void headerBar(int x, int y, int value, int max_value){
        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 20, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(value));
        pointNumber.putString(x + 21, y, "/");
        pointNumber.putString(x + 22, y, String.valueOf(max_value));
    }

    public void fillBar(int x, int y, int value, int max_value) {
        for (int i = 0; i <= 16; i += 4) {
            if ((i / 4) * 3 <= (value / (float) max_value) * 12)
                filled_point.putString(x + i, y, "|");
            else
                unfilled_point.putString(x + i, y, "|");
        }
        for (int j = 0; j <= 11; j++) {
            if (j < (value / (float) max_value) * 12)
                filled_point.putString(x + j + j / 3 + 1, y, "_");
            else
                unfilled_point.putString(x + j + j / 3 + 1, y, "_");
        }
    }
}
