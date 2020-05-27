package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Titles;
import com.g13.view.menus.TitlesViewer;
import com.g13.view.menus.LevelViewer;

import java.io.IOException;

import static com.g13.view.menus.TitlesViewer.COMMAND.*;
import static com.g13.view.menus.TitlesViewer.COMMAND.QUIT;

public abstract class TitlesController implements Controller {
    protected Titles model;
    protected TitlesViewer view;
    protected StateRecognizer recognizer;

    public TitlesController(Titles model, TitlesViewer view, StateRecognizer recognizer){
        this.model = model;
        this.view = view;
        this.recognizer = recognizer;
    }

    @Override
    public void start() throws IOException {
        LevelViewer.COMMAND command = view.getNextCommand();

        if (command == DOWN) moveDown();
        else if (command == UP) moveUp();
        else if (command == SELECT) {
            recognizer.getCurrentState().advance();
            return;
        }
        else if (command == QUIT) model.setFinished(true);
        view.draw();
    }

    public abstract void moveDown();
    public abstract void moveUp();
}