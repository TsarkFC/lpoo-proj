package com.g13.controller.state;

import java.io.IOException;

public class StateRecognizer {
    private GameState gameState;
    private StartState startState;
    private MenuState menuState;
    private State currentState;

    public StateRecognizer() throws IOException {
        gameState = new GameState();
        startState = new StartState();
        menuState = new MenuState();
        currentState = gameState;
    }
    public State getCurrentState() { return currentState; }
    public void setCurrentState(State currentState) { this.currentState = currentState; }
}
