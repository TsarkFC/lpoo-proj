package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.titles.start.StartController;
import com.g13.model.Model;
import com.g13.model.titles.start.Start;
import com.g13.view.View;
import com.g13.view.titles.start.StartViewer;

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
        else if (start.getSelection() == 1)
            System.out.println("Instructions");
    }
}
