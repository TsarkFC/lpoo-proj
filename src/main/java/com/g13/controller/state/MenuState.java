package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.menus.LevelController;
import com.g13.model.Model;
import com.g13.model.menus.Level;
import com.g13.view.View;
import com.g13.view.menus.LevelViewer;

import java.io.IOException;

public class MenuState implements State{
    private Level level;
    private LevelViewer levelViewer;
    private LevelController levelController;
    private StateRecognizer recognizer;

    public MenuState(StateRecognizer recognizer) throws IOException {
        this.recognizer = recognizer;
        level = new Level();
        levelViewer = new LevelViewer(level, recognizer.getScreen());
        levelController = new LevelController(level, levelViewer, recognizer);
    }

    @Override
    public Model getModel() { return level; }

    @Override
    public View getView() { return levelViewer; }

    @Override
    public Controller getController() {
        return levelController;
    }

    @Override
    public void advance() throws IOException {
        recognizer.setCurrentState(recognizer.getGameState());
        recognizer.getCurrentState().getView().draw();
    }
}
