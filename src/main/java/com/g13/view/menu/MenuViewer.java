package com.g13.view.menu;

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
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class MenuViewer implements View {
    private Menu model;
    private TerminalScreen screen;
    private TextGraphics graphics;
    private TextGraphics greenGraphics;
    private TextGraphics redGraphics;

    public enum COMMAND {
        RIGHT,
        LEFT,
        SELECT,
        NOTHING
    }

    public MenuViewer(Menu model) throws IOException {
        this.model = model;
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(70, 40)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

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

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(70, 40), ' ');
        for (Stage stage : model.getStages()){
            if (stage.isUnlocked()) greenGraphics.putString(stage.getX(), stage.getY(), " ");
            else redGraphics.putString(stage.getX(), stage.getY(), " ");
        }
        screen.refresh();
    }

    @Override
    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.ArrowRight) return COMMAND.RIGHT;
        else if (input.getKeyType() == KeyType.ArrowLeft) return COMMAND.LEFT;
        else if (input.getKeyType() == KeyType.Enter) return COMMAND.SELECT;
        else return COMMAND.NOTHING;
    }
}
