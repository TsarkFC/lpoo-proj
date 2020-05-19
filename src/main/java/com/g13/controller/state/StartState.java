package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.model.Model;
import com.g13.view.View;

public class StartState implements State{
    private StateRecognizer recognizer;

    public StartState(StateRecognizer recognizer){
        this.recognizer = recognizer;
    }

    @Override
    public Model getModel() { return null; }

    @Override
    public View getView() { return null; }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    public void advance() { recognizer.setCurrentState(recognizer.getMenuState()); }
}
