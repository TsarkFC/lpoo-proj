package com.g13.controller.state.statefactory;


import com.g13.controller.menus.StartController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Start;
import com.g13.view.menus.StartViewer;

public class StartStateFactory {
    private Start start;
    private StartViewer startViewer;
    private StartController startController;

    public StartStateFactory(StateRecognizer recognizer){
        start = new Start();
        startViewer = new StartViewer(start, recognizer.getScreen());
        startController = new StartController(start, startViewer, recognizer);
    }

    public Start getStart() { return start; }
    public StartViewer getStartViewer() { return startViewer; }
    public StartController getStartController() { return startController; }
}
