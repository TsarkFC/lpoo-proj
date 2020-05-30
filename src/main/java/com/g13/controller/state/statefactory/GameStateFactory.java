package com.g13.controller.state.statefactory;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.view.arena.ArenaViewer;
import com.g13.view.arena.ComponentFactory;

public class GameStateFactory {
    private Arena arena;
    private ArenaViewer arenaViewer;
    private ArenaController arenaController;

    public GameStateFactory(StateRecognizer recognizer){
        arena = new Arena(50, 30);
        arenaViewer = new ArenaViewer(arena, recognizer.getScreen(), new ComponentFactory(recognizer.getScreen()));
        arenaController = new ArenaController(arenaViewer, arena, recognizer);
    }

    public Arena getArena() { return arena; }
    public ArenaViewer getArenaViewer() { return arenaViewer; }
    public ArenaController getArenaController() { return arenaController; }
}
