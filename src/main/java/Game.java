import observer.ArenaObserver;
import commands.Command;
import creator.ArenaCreator;
import model.*;
import view.Gui;

import java.io.IOException;

public class Game implements ArenaObserver {
    private Gui gui;
    private Arena arena;
    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    private void start() throws IOException {
        ArenaCreator creator = new ArenaCreator();

        Arena arena = creator.create();
        arena.addObserver(this);
        gui = new Gui(arena);
        gui.draw();

        while(!arena.isFinished()){
            Command command = gui.getNextCommand();
            command.execute();
        }
    }

    @Override
    public void arenaChanged() {
        try{
            gui.draw();
        } catch (IOException e) {

        }
    }
}
