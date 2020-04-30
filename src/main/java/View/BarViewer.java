package view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BarViewer {
    private TextGraphics unfilled_point;
    private TextGraphics filled_point;
    private TextGraphics pointNumber;

    public BarViewer(TerminalScreen screen){
        unfilled_point = screen.newTextGraphics();
        filled_point = screen.newTextGraphics();
        pointNumber = screen.newTextGraphics();
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

        fillBar(x, y, filled_point, unfilled_point, point_val, max_point_val);

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(point_val));
        pointNumber.putString(x + 20, y, "/");
        pointNumber.putString(x + 21, y, String.valueOf(max_point_val));
    }

    public void drawHealthBar(int x, int y, int health, int max_health){
        setAllGraphicsValues("#BB1111", "#AA5555", "#775555", "#777777", "#BB1111", "#FFFFFF");
        fillBar(x, y, filled_point, unfilled_point, health, max_health);
        headerBar(x, y, pointNumber, health, max_health);
        pointNumber.putString(x - 3, y, "HP:");
    }

    public void drawManaBar(int x, int y, int mana, int max_mana){
        setAllGraphicsValues("#3D84CC", "#5555AA", "#6688AA", "#777777", "#3D84CC", "#FFFFFF");
        fillBar(x, y, filled_point, unfilled_point, mana, max_mana);
        headerBar(x, y, pointNumber, mana, max_mana);
        pointNumber.putString(x - 5, y, "Mana:");
    }

    public void headerBar(int x, int y, TextGraphics pointNumber, int value, int max_value){
        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 20, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(value));
        pointNumber.putString(x + 21, y, "/");
        pointNumber.putString(x + 22, y, String.valueOf(max_value));
    }

    public void fillBar(int x, int y, TextGraphics filled_point, TextGraphics unfilled_point, int value, int max_value) {
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
