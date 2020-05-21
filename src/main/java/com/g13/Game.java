package com.g13;

import com.g13.controller.state.StateRecognizer;
import com.g13.view.ScreenFactory;
import com.g13.view.View;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        ScreenFactory factory = new ScreenFactory();
        StateRecognizer recognizer = new StateRecognizer(factory.getScreen());
        recognizer.getCurrentState().getView().draw();
        while(!recognizer.getCurrentState().getModel().isFinished())
            recognizer.getCurrentState().getController().start();
    }
}
