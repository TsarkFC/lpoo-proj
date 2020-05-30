package com.g13.view.arena;

import com.g13.model.arena.Arena;
import com.g13.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class ArenaViewer implements View {
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
        PLAYCARD
    }

    public ArenaViewer(Arena arena, TerminalScreen screen) {
        this.screen = screen;

        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        this.arena = arena;
        this.cardViewer = new CardViewer(screen.newTextGraphics());
        this.barViewer = new BarViewer(screen.newTextGraphics(), screen.newTextGraphics(), screen.newTextGraphics());
        this.gameParticipantViewer = new GameParticipantViewer(barViewer, cardViewer, graphics);
    }

    @Override
    public void draw() throws IOException {
        screen.clear();
        drawBackground();
        gameParticipantViewer.drawPlayer(arena.getPlayer());
        gameParticipantViewer.drawEnemy(arena.getEnemy());
        screen.refresh();
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
        else if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') {
            return COMMAND.ONE;
        }
        else if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') {
            return COMMAND.TWO;
        }
        else if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') {
            return COMMAND.THREE;
        }
        else if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') {
            return COMMAND.FOUR;
        }
        else if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'd') {
            return COMMAND.DRAW;
        }
        else if (input.getKeyType() == KeyType.Enter) {
            return COMMAND.SWITCH;
        }
        else if(input.getKeyType() == KeyType.Tab) {
            return COMMAND.PLAYCARD;
        }
        return COMMAND.NOTHING;
    }

}
