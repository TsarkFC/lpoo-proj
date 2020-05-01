package com.g13;

import com.g13.controller.ArenaController;
import com.g13.creator.ArenaCreator;
import com.g13.model.Arena;
import com.g13.view.Gui;

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
        creator.create(controller);
        controller.start();
    }
}
