package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Start;
import com.g13.view.menus.StartViewer;

import java.io.IOException;

public class StartController extends MenuController implements Controller {
    private Start model;
    private StartViewer view;

    public StartController(Start model, StartViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
        this.view = view;
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

    @Override
    public void render() throws IOException {
        view.draw();
    }

    @Override
    public boolean isFinished() {
        return model.isFinished();
    }
}
