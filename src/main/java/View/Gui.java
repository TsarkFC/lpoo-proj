package View;

import Commands.*;
import Model.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.w3c.dom.Text;

import java.io.IOException;

public class Gui {
    private Arena arena;
    private TerminalScreen screen;

    private CardViewer cardViewer;
    private GameParticipantViewer gameParticipantViewer;

    public Gui(Arena arena) throws IOException {
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

        this.arena = arena;
        this.cardViewer = new CardViewer();
        this.gameParticipantViewer = new GameParticipantViewer(screen, cardViewer);
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

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');
    }

    public Command getNextCommand() throws IOException {
        KeyStroke input = screen.readInput();

        if (input.getKeyType() == KeyType.EOF) return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'q') return new QuitCommand(arena, screen);
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '1') cardViewer.drawCardInfo(0, screen, arena.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '2') cardViewer.drawCardInfo(1, screen, arena.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '3') cardViewer.drawCardInfo(2, screen, arena.getPlayer());
        if (input.getKeyType() == KeyType.Character && input.getCharacter() == '4') cardViewer.drawCardInfo(3, screen, arena.getPlayer());


        if(arena.getCurrent()) {
            if (input.getKeyType() == KeyType.Character && input.getCharacter() == 'd')
                return new DrawCardCommand(arena, arena.getPlayer());
            if (input.getKeyType() == KeyType.Enter) return new SwitchPlayerCommand(arena);
        }

        return new DoNothingCommand();
    }

}
