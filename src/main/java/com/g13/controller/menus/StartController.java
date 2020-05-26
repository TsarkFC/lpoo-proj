package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Start;
import com.g13.view.menus.StartViewer;

public class StartController extends TitlesController implements Controller {
    private Start model;

    public StartController(Start model, StartViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
    }

    @Override
    public void moveDown() {
        if (model.getSelection() < 1)
            model.selectionMoveDown();
    }

    @Override
    public void moveUp() {
        if (model.getSelection() > 0)
            model.selectionMoveUp();
    }
}
