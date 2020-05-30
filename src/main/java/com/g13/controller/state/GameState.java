package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.creator.ArenaCreator;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.statefactory.GameStateFactory;
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

    public GameState(StateRecognizer recognizer, GameStateFactory factory)  {
        this.recognizer = recognizer;
        arena = factory.getArena();
        arenaViewer = factory.getArenaViewer();
        arenaController = factory.getArenaController();
        ArenaCreator creator = new ArenaCreator();
        creator.create(arenaController);
    }

    @Override
    public Model getModel() { return arena; }

    @Override
    public View getView() { return arenaViewer; }

    @Override
    public Controller getController() {return arenaController;}

    @Override
    public void advance() throws IOException {
        if (arenaController.getPlayerController().getHealth() <= 0) {
            recognizer.getLevelState().lockStages();
            recognizer.setLevelState();
            arenaController.getEnemyController().resetPlayer();
            arenaController.getPlayerController().resetPlayer();
        }
        else if (arenaController.getEnemyController().getHealth() <= 0){
            recognizer.getLevelState().unlockNextStage();
            recognizer.setLevelState();
            arenaController.getPlayerController().resetOnWin();
            arenaController.getEnemyController().resetPlayer();
        }
    }

    public void setStrategy(PlayStrategy strategy){
        arenaController.setEnemyStrategy(strategy);
    }
}
