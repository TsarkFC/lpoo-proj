package com.g13.controller.state;

import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.statefactory.GameStateFactory;
import com.g13.controller.state.statefactory.LevelStateFactory;
import com.g13.controller.state.statefactory.StartStateFactory;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class StateRecognizer {
    private GameState gameState;
    private StartState startState;
    private LevelState levelState;
    private State currentState;
    private TerminalScreen screen;

    public StateRecognizer(TerminalScreen screen) {
        this.screen = screen;
        startState = new StartState(this, new StartStateFactory(this));
        levelState = new LevelState(this, new LevelStateFactory(this));
        gameState = new GameState(this, new GameStateFactory(this));
        currentState = startState;
    }
    public State getCurrentState() { return currentState; }
    public LevelState getLevelState() { return levelState; }

    public void setGameState(PlayStrategy strategy) {
        gameState.setStrategy(strategy);
        currentState = gameState;
    }
    public void setLevelState() {
        currentState = levelState;
    }
    public void setStartState()  {
        currentState = startState;
    }

    public TerminalScreen getScreen() { return screen; }
}
