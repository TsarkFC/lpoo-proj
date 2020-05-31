package com.g13.controller.state.statefactory;

import com.g13.controller.menus.LevelController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.view.menus.LevelViewer;

public class LevelStateFactory {
    private Level level;
    private LevelViewer levelViewer;
    private LevelController levelController;

    public LevelStateFactory(StateRecognizer recognizer) {
        level = new Level();
        levelViewer = new LevelViewer(level, recognizer.getScreen());
        levelController = new LevelController(level, levelViewer, recognizer);
    }

    public Level getLevel() { return level; }
    public LevelController getLevelController() { return levelController; }
}
