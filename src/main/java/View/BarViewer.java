package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BarViewer {
    private TerminalScreen screen;

    public BarViewer(TerminalScreen screen){
        this.screen = screen;
    }

    public void drawPointBar(int x, int y, int point_val, int max_point_val){ //TODO: Variable maximum

        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#AA8855"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        if(point_val == max_point_val){
            filled_point.setBackgroundColor(TextColor.Factory.fromString("#FFEECC"));
            filled_point.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        }

        fillBar(x, y, filled_point, unfilled_point, point_val, max_point_val);

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#AA8855"));

        pointNumber.putString(x + 19, y, " ");
        pointNumber.putString(x + 18, y, String.valueOf(point_val));
        pointNumber.putString(x + 20, y, "/");
        pointNumber.putString(x + 21, y, String.valueOf(max_point_val));
    }

    public void drawHealthBar(int x, int y, int health, int max_health){
        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#775555"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#777777"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#AA5555"));

        fillBar(x, y, filled_point, unfilled_point, health, max_health);

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#BB1111"));
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        headerBar(x, y, pointNumber, health, max_health);

        pointNumber.putString(x - 3, y, "HP:");

    }

    public void drawManaBar(int x, int y, int mana, int max_mana){
        TextGraphics unfilled_point = screen.newTextGraphics();
        unfilled_point.setBackgroundColor(TextColor.Factory.fromString("#6688AA"));
        unfilled_point.setForegroundColor(TextColor.Factory.fromString("#777777"));


        TextGraphics filled_point = screen.newTextGraphics();
        filled_point.setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        filled_point.setForegroundColor(TextColor.Factory.fromString("#5555AA"));

        fillBar(x, y, filled_point, unfilled_point, mana, max_mana);

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#3D84CC"));
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

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
            if ((i / 4) * 3 <= (value / (float) max_value) * 12) {
                filled_point.putString(x + i, y, "|");
            } else {
                unfilled_point.putString(x + i, y, "|");
            }

        }

        for (int j = 0; j <= 11; j++) {
            if (j < (value / (float) max_value) * 12) {
                filled_point.putString(x + j + j / 3 + 1, y, "_");
            } else {
                unfilled_point.putString(x + j + j / 3 + 1, y, "_");
            }
        }
    }
}
