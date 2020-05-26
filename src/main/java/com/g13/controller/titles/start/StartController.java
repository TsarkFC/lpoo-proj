package com.g13.controller.titles.start;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.controller.titles.TitlesController;
import com.g13.model.titles.start.Button;
import com.g13.model.titles.start.Start;
import com.g13.view.titles.start.StartViewer;

public class StartController extends TitlesController implements Controller {
    private Start model;

    public StartController(Start model, StartViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
    }

    @Override
    public void moveDown() {
        if (model.getSelection() < 2)
            model.selectionMoveDown();
    }

    @Override
    public void moveUp() {
        if (model.getSelection() > 0)
            model.selectionMoveUp();
    }
}
