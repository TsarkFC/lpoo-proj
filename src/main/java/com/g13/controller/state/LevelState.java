package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.arena.strategies.AggressivePlayStrategy;
import com.g13.controller.arena.strategies.CarefulPlayStrategy;
import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.controller.menus.LevelController;
import com.g13.controller.state.statefactory.LevelStateFactory;
import com.g13.model.menus.Level;

public class LevelState implements State{
    private final Level level;
    private final LevelController levelController;
    private final StateRecognizer recognizer;

    public LevelState(StateRecognizer recognizer, LevelStateFactory factory){
        this.recognizer = recognizer;
        level = factory.getLevel();
        levelController = factory.getLevelController();
    }

    @Override
    public Controller getController() {
        return levelController;
    }

    @Override
    public void advance() {
        if (level.getCross() == 0){
            recognizer.setStartState();
            return;
        }

        if (level.getCross() == 1)
            recognizer.setGameState(new CarefulPlayStrategy());
        else if (level.getCross() == 2)
            recognizer.setGameState(new NormalPlayStrategy());
        else if (level.getCross() == 3)
            recognizer.setGameState(new AggressivePlayStrategy());
    }

    public void unlockNextStage(){
        levelController.unlockNextStage(level.getCross());
    }
    public void lockStages() { levelController.lockStages(); }
}
