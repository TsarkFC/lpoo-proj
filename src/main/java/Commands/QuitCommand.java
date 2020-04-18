package Commands;

import Commands.Command;
import Model.Arena;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class QuitCommand implements Command {
    private final Arena arena;
    private final Screen screen;

    public QuitCommand(Arena arena, Screen screen) {
        this.arena = arena;
        this.screen = screen;
    }


    public void execute() {
        arena.finish();
        try {
            screen.close();
        } catch (IOException e) {
            // If we fail to close the screen
            // there is nothing we can do about it.
        }
    }
}