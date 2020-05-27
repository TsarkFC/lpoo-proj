package com.g13.view.menus;

import com.g13.model.menus.button.Button;
import com.g13.model.menus.Menu;
import com.g13.view.View;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public abstract class MenuViewer implements View {
    protected Menu model;
    protected TerminalScreen screen;
    protected TextGraphics graphics;
    protected TextGraphics brownGraphics;
    protected TextGraphics redGraphics;
    public enum COMMAND {
        DOWN,
        UP,
        SELECT,
        NOTHING,
        QUIT
    }

    public MenuViewer(Menu model, TerminalScreen screen){
        this.model = model;
        this.screen = screen;
        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        brownGraphics = screen.newTextGraphics();
        brownGraphics.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        brownGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        redGraphics = screen.newTextGraphics();
        redGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        redGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
    }

    protected void drawTitle(){
        int x = 17, y = 1;
        char title_void[][] = model.getTitle_void();
        char title_tyrant[][] = model.getTitle_tyrant();

        drawGenericTitle(x,y,title_void);
        x = 13;
        y = 5;
        drawGenericTitle(x,y,title_tyrant);
    }

    private void drawGenericTitle(int x, int y, char[][] title){
        int safex = x;
        for (int i = 0; i < title.length; i++){
            for (int j = 0; j < title[i].length; j++){
                if (title[i][j] == '*')
                    brownGraphics.putString(x, y, " ");
                else
                    graphics.putString(x, y, " ");
                x += 1;
            }
            x = safex;
            y += 1;
        }
    }

    @Override
    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.ArrowDown) return COMMAND.DOWN;
        else if (input.getKeyType() == KeyType.ArrowUp) return COMMAND.UP;
        else if (input.getKeyType() == KeyType.Enter) return COMMAND.SELECT;
        else if (input.getKeyType() == KeyType.EOF ||
                input.getKeyType() == KeyType.Character && input.getCharacter() == 'q'){
            screen.close();
            return COMMAND.QUIT;
        }
        else return COMMAND.NOTHING;
    }

    protected void drawButton(Button button, boolean condition, TextGraphics opposite){
        if (condition)
            brownGraphics.putString(button.getX(), button.getY(), button.getMessage());
        else
            opposite.putString(button.getX(), button.getY(), button.getMessage());
    }
}
