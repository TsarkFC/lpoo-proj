package com.g13;

import com.g13.controller.state.StateRecognizer;
import com.g13.view.ScreenFactory;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        StateRecognizer recognizer = new StateRecognizer(new ScreenFactory().getScreen());
        recognizer.getCurrentState().getView().draw();
        while(!recognizer.getCurrentState().getModel().isFinished())
            recognizer.getCurrentState().getController().start();
    }
}
