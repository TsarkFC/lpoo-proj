package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.creator.ArenaCreator;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.statefactory.GameStateFactory;

public class GameState implements State{
    private ArenaController arenaController;
    private StateRecognizer recognizer;

    public GameState(StateRecognizer recognizer, GameStateFactory factory)  {
        this.recognizer = recognizer;
        arenaController = factory.getArenaController();
        ArenaCreator creator = new ArenaCreator();
        creator.create(arenaController);
    }

    @Override
    public Controller getController() {return arenaController;}

    @Override
    public void advance()  {
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
