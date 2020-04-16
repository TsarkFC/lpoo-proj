import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Gui {
    private Arena arena;
    private TerminalScreen screen;

    public Gui(Arena arena) throws IOException {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.arena = arena;
    }

    public void draw() throws IOException {
        screen.clear();
        drawBackground();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        graphics.putString(10, 10, "X");
        graphics.putString(10, 15, "Y");

        screen.refresh();
    }

    public void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth(), arena.getHeight()),
                ' '
        );
    }
}
