package com.g13.view.arena;

import com.g13.controller.state.State;
import com.g13.model.arena.Arena;
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
import com.g13.controller.arena.observer.ArenaObserver;

import java.io.IOException;

public class ArenaViewer implements ArenaObserver, View {
    private TerminalScreen screen;
    private CardViewer cardViewer;
    private BarViewer barViewer;
    private GameParticipantViewer gameParticipantViewer;
    private Arena arena;
    private TextGraphics graphics;

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

    public ArenaViewer(Arena arena) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(arena.getWidth(), arena.getHeight())).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        this.arena = arena;
        this.cardViewer = new CardViewer(screen.newTextGraphics());
        this.barViewer = new BarViewer(screen.newTextGraphics(), screen.newTextGraphics(), screen.newTextGraphics());
        this.gameParticipantViewer = new GameParticipantViewer(barViewer, cardViewer, graphics);
    }

    public void draw() throws IOException {
        screen.clear();
        drawBackground();

        gameParticipantViewer.drawPlayer(arena.getPlayer());
        gameParticipantViewer.drawEnemy(arena.getEnemy());

        screen.refresh();
    }

    @Override
    public void arenaChanged() throws IOException {
        draw();
    }

    private void drawBackground(){
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
