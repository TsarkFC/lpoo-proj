package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.creator.ArenaCreator;
import com.g13.model.Model;
import com.g13.model.arena.Arena;
import com.g13.view.View;
import com.g13.view.arena.ArenaViewer;

import java.io.IOException;

public class GameState implements State{
    private Arena arena;
    private ArenaViewer arenaViewer;
    private ArenaController arenaController;

    public GameState() throws IOException {
        arena = new Arena(50, 30);
        arenaViewer = new ArenaViewer(arena);
        arena.addObserver(arenaViewer);
        arenaController = new ArenaController(arenaViewer, arena);
        ArenaCreator creator = new ArenaCreator();
        creator.create(arenaController);
    }

    public Controller getController() {return arenaController;}
}
