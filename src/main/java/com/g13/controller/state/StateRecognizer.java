package com.g13.controller.state;

import com.g13.view.menu.MenuViewer;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class StateRecognizer {
    private GameState gameState;
    private StartState startState;
    private MenuState menuState;
    private State currentState;
    private TerminalScreen screen;

    public StateRecognizer(TerminalScreen screen) throws IOException {
        this.screen = screen;
        startState = new StartState(this);
        menuState = new MenuState(this);
        gameState = new GameState(this);
        currentState = menuState;
    }
    public State getCurrentState() { return currentState; }
    public void setCurrentState(State currentState) { this.currentState = currentState; }

    public GameState getGameState() { return gameState; }
    public MenuState getMenuState() { return menuState; }

    public TerminalScreen getScreen() { return screen; }
}
