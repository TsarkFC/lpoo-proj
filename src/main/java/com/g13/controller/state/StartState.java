package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.menus.StartController;
import com.g13.controller.state.statefactory.StartStateFactory;
import com.g13.model.menus.Start;

public class StartState implements State{
    private Start start;;
    private StartController startController;
    private StateRecognizer recognizer;

    public StartState(StateRecognizer recognizer, StartStateFactory factory){
        this.recognizer = recognizer;
        start = factory.getStart();
        startController = factory.getStartController();
    }

    @Override
    public Controller getController() {
        return startController;
    }

    @Override
    public void advance() {
        if (start.getSelection() == 0)
            recognizer.setLevelState();
        else if (start.getSelection() == -1)
            start.setSelection(1);
        else if (start.getSelection() == 1)
            start.setSelection(-1);
    }
}
