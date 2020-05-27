package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.creator.ArenaCreator;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.model.Model;
import com.g13.model.arena.Arena;
import com.g13.view.View;
import com.g13.view.arena.ArenaViewer;

import java.io.IOException;

public class GameState implements State{
    private Arena arena;
    private ArenaViewer arenaViewer;
    private ArenaController arenaController;
    private StateRecognizer recognizer;

    public GameState(StateRecognizer recognizer)  {
        arena = new Arena(50, 30);
        arenaViewer = new ArenaViewer(arena, recognizer.getScreen());
        arenaController = new ArenaController(arenaViewer, arena, recognizer);
        ArenaCreator creator = new ArenaCreator();
        creator.create(arenaController);
        this.recognizer = recognizer;
    }

    @Override
    public Model getModel() { return arena; }

    @Override
    public View getView() { return arenaViewer; }

    @Override
    public Controller getController() {return arenaController;}

    @Override
    public void advance() { recognizer.setCurrentState(recognizer.getLevelState()); }

    public void setStrategy(PlayStrategy strategy){
        arenaController.setEnemyStrategy(strategy);
    }
}
