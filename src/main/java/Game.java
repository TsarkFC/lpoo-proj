import controller.ArenaController;
import creator.ParticipantCreator;
import observer.ArenaObserver;
import commands.Command;
import creator.ArenaCreator;
import model.*;
import view.Gui;

import java.io.IOException;

public class Game {
    private Gui gui;
    private Arena arena;
    public static void main(String[] args) throws IOException {
        Arena arena = new Arena(50, 30);
        Gui gui = new Gui(arena);
        arena.addObserver(gui);

        ArenaController controller = new ArenaController(gui, arena);
        ArenaCreator creator = new ArenaCreator();
        ParticipantCreator pcreator = new ParticipantCreator();
        creator.create(controller, pcreator);
        controller.start();
    }
}
