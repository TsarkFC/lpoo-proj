package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.menus.LevelViewer;

import java.io.IOException;

public class LevelController extends MenuController implements Controller {
    private Level model;
    private LevelViewer view;

    public LevelController(Level model, LevelViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
        this.view = view;
    }

    @Override
    public void moveDown(){ if (model.getCross() < 3 && model.getNextCross()) model.crossMoveDown(); }
    @Override
    public void moveUp() { if (model.getCross() > 0) model.crossMoveUp(); }

    public void unlockNextStage(int enemy_position){
        if (enemy_position == 2 || enemy_position == 1)
            model.getStages().get(enemy_position+1).setUnlocked(true);
    }

    public void lockStages(){
        model.resetCross();
        model.getStages().get(2).setUnlocked(false);
        model.getStages().get(3).setUnlocked(false);
    }

    @Override
    public void render() throws IOException {
        view.draw();
    }

    @Override
    public boolean isFinished() {
        return model.isFinished();
    }
}
