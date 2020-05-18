package com.g13;

import com.g13.controller.state.StateRecognizer;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        StateRecognizer recognizer = new StateRecognizer();
        recognizer.getCurrentState().getController().start();
    }
}
