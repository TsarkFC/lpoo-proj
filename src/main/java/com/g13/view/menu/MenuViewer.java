package com.g13.view.menu;

import com.g13.controller.arena.observer.Observer;
import com.g13.model.menu.Menu;
import com.g13.model.menu.Stage;
import com.g13.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class MenuViewer implements View, Observer {
    private Menu model;
    private TerminalScreen screen;
    private TextGraphics graphics;
    private TextGraphics greenGraphics;
    private TextGraphics redGraphics;

    @Override
    public void modelChanged() throws IOException {
        draw();
    }

    public enum COMMAND {
        DOWN,
        UP,
        SELECT,
        NOTHING,
        QUIT
    }

    public MenuViewer(Menu model, TerminalScreen screen) throws IOException {
        this.model = model;
        this.screen = screen;

        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        greenGraphics = screen.newTextGraphics();
        greenGraphics.setBackgroundColor(TextColor.Factory.fromString("#00FF00"));
        greenGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));

        redGraphics = screen.newTextGraphics();
        redGraphics.setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        redGraphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
    }

    @Override
    public void draw() throws IOException {
        screen.clear();

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        for (Stage stage : model.getStages()){
            if (stage.isUnlocked()) greenGraphics.putString(stage.getX(), stage.getY(), " ");
            else redGraphics.putString(stage.getX(), stage.getY(), " ");
        }
        greenGraphics.putString(model.getX(), (model.getCross()+1)*5+5, "x");
        screen.refresh();
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

    public TerminalScreen getFirstScreen() { return screen;}
}
