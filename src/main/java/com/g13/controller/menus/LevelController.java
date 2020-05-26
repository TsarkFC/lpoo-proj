package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.menus.LevelViewer;

public class LevelController extends TitlesController implements Controller {
    private Level model;

    public LevelController(Level model, LevelViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
    }

    @Override
    public void moveDown(){ if (model.getCross() < 3 && model.getNextCross()) model.crossMoveDown(); }
    @Override
    public void moveUp() { if (model.getCross() > 0) model.crossMoveUp(); }

    public void unlockNextStage(){
        for (Stage stage : model.getStages())
            if (!stage.isUnlocked()){
                stage.setUnlocked(true);
                break;
            }

    }
}
