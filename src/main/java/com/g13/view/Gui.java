package com.g13.view;

import com.g13.model.Arena;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.g13.controller.observer.ArenaObserver;

import java.io.IOException;

public class Gui implements ArenaObserver {
    private TerminalScreen screen;
    private CardViewer cardViewer;
    private BarViewer barViewer;
    private GameParticipantViewer gameParticipantViewer;
    private Arena arena;

    public enum COMMAND {
        SWITCH,
        NOTHING,
        DRAW,
        QUIT,
        ONE,
        TWO,
        THREE,
        FOUR,
        PLAYCARD1,
        PLAYCARD2,
        PLAYCARD3,
        PLAYCARD4,
        NOPLAYCARD
    }
    COMMAND specialCmd = COMMAND.NOPLAYCARD;
    COMMAND safeSpecialCmd;

    public Gui(Arena arena){
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(arena.getWidth(), arena.getHeight())).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            this.arena = arena;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.cardViewer = new CardViewer(screen.newTextGraphics());
        this.barViewer = new BarViewer(screen.newTextGraphics(), screen.newTextGraphics(), screen.newTextGraphics());
        this.gameParticipantViewer = new GameParticipantViewer(barViewer, cardViewer);
    }

    public void draw() throws IOException {
        screen.clear();
        drawBackground();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        gameParticipantViewer.drawPlayer(arena.getPlayer());
        gameParticipantViewer.drawEnemy(arena.getEnemy());

        screen.refresh();
    }

    @Override
    public void arenaChanged() {
        try{
            draw();
        } catch (IOException e) {

        }
    }

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');
    }

    public COMMAND getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF ||
            input.getKeyType() == KeyType.Character && input.getCharacter() == 'q'){
            screen.close();
            return COMMAND.QUIT;
        }

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') {
            specialCmd = COMMAND.PLAYCARD1;
            return COMMAND.ONE;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') {
            specialCmd = COMMAND.PLAYCARD2;
            return COMMAND.TWO;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') {
            specialCmd = COMMAND.PLAYCARD3;
            return COMMAND.THREE;
        }
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') {
            specialCmd = COMMAND.PLAYCARD4;
            return COMMAND.FOUR;
        }

        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'd') {
            specialCmd = COMMAND.NOPLAYCARD;
            return COMMAND.DRAW;
        }
        if (input.getKeyType() == KeyType.Enter){
            specialCmd = COMMAND.NOPLAYCARD;
            return COMMAND.SWITCH;
        }

        if(input.getKeyType() == KeyType.Tab){
            safeSpecialCmd = specialCmd;
            specialCmd = COMMAND.NOPLAYCARD;
            return safeSpecialCmd;
        }

        return COMMAND.NOTHING;
    }

}
