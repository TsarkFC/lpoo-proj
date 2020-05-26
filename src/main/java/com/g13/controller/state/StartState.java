package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.menus.StartController;
import com.g13.model.Model;
import com.g13.model.menus.Start;
import com.g13.view.View;
import com.g13.view.menus.StartViewer;

import java.io.IOException;

public class StartState implements State{
    private Start start;
    private StartViewer startViewer;
    private StartController startController;
    private StateRecognizer recognizer;

    public StartState(StateRecognizer recognizer){
        this.recognizer = recognizer;
        start = new Start();
        startViewer = new StartViewer(start, recognizer.getScreen());
        startController = new StartController(start, startViewer, recognizer);
    }

    @Override
    public Model getModel() { return start; }

    @Override
    public View getView() { return startViewer; }

    @Override
    public Controller getController() {
        return startController;
    }

    @Override
    public void advance() throws IOException {
        if (start.getSelection() == 0)
            recognizer.setMenuState();
        else if (start.getSelection() == -1){
            start.setSelection(1);
            recognizer.getCurrentState().getView().draw();
        }
        else if (start.getSelection() == 1){
            start.setSelection(-1);
            startViewer.drawInstructions();
        }
    }
}
