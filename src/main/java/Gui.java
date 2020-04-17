import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

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

        drawPlayer(arena.getPlayer());
        drawEnenmy(arena.getEnemy());

        screen.refresh();
    }

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(
                new TerminalPosition(0, 0),
                new TerminalSize(arena.getWidth(), arena.getHeight()),
                ' '
        );
    }

    private void drawPlayer(Player player){
        drawCard(1, 19, player.getDraw_deck().get(0), true);


    }

    private void drawEnenmy(Enemy enemy){
        drawCard(1, 0, enemy.getDraw_deck().get(0), false);
    }

    private void drawCard(int x, int y, Card card, boolean player){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        graphics.putString(x+1, y+3, "_");
        graphics.putString(x+2, y+3, "_");
        graphics.putString(x+3, y+3, "_");
        graphics.putString(x+1, y, "_");
        graphics.putString(x+2, y, "_");
        graphics.putString(x+3, y, "_");
        graphics.putString(x, y+3, "|");
        graphics.putString(x, y+2, "|");
        graphics.putString(x, y+1, "|");
        graphics.putString(x+4, y+3, "|");
        graphics.putString(x+4, y+2, "|");
        graphics.putString(x+4, y+1, "|");
        if (player) graphics.putString(3, 21, card.getValueString()); //change later -> separate functions
    }


}
