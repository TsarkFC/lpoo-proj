import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Gui {
    private TerminalScreen screen;

    public Gui() throws IOException, InterruptedException {
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

        screen.clear();
        screen.setCharacter(10, 10, new TextCharacter('X'));
        screen.refresh();

        TimeUnit.SECONDS.sleep(2);

        screen.close();
    }
}
