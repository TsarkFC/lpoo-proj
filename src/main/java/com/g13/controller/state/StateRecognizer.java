package com.g13.controller.state;

import java.io.IOException;

public class StateRecognizer {
    private GameState gameState;
    private StartState startState;
    private MenuState menuState;
    private State currentState;

    public StateRecognizer() throws IOException {
        gameState = new GameState(this);
        startState = new StartState(this);
        menuState = new MenuState(this);
        currentState = gameState;
    }
    public State getCurrentState() { return currentState; }
    public void setCurrentState(State currentState) { this.currentState = currentState; }

    public GameState getGameState() { return gameState; }
    public MenuState getMenuState() { return menuState; }
}
