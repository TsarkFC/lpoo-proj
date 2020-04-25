package view;

import commands.*;
import controller.ArenaController;
import model.Arena;
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

public class Gui {
    private ArenaController arenaController;
    private TerminalScreen screen;

    private CardViewer cardViewer;
    private GameParticipantViewer gameParticipantViewer;

    public Gui(Arena arena){
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(arena.getWidth(), arena.getHeight())).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.arenaController = new ArenaController(arena);
        this.cardViewer = new CardViewer(screen);
        this.gameParticipantViewer = new GameParticipantViewer(screen, cardViewer);
    }

    public void draw() throws IOException {
        screen.clear();
        drawBackground();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        gameParticipantViewer.drawPlayer(arenaController.getPlayer());
        gameParticipantViewer.drawEnemy(arenaController.getEnemy());

        screen.refresh();
    }

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arenaController.getWidth(), arenaController.getHeight()), ' ');
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arenaController.getModel(), screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arenaController.getModel(), screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') cardViewer.drawCardInfo(0, screen, arenaController.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') cardViewer.drawCardInfo(1, screen, arenaController.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') cardViewer.drawCardInfo(2, screen, arenaController.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') cardViewer.drawCardInfo(3, screen, arenaController.getPlayer());


        if(arenaController.getCurrent()) {
            if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'd')
                return new DrawCardCommand(arenaController.getModel(), arenaController.getPlayer());
            if (input.getKeyType() == KeyType.Enter) return new SwitchPlayerCommand(arenaController.getModel());
        }

        return new DoNothingCommand();
    }

}
