package com.g13.controller.menus;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menus.Menu;
import com.g13.view.menus.MenuViewer;
import com.g13.view.menus.LevelViewer;

import java.io.IOException;

import static com.g13.view.menus.MenuViewer.COMMAND.*;
import static com.g13.view.menus.MenuViewer.COMMAND.QUIT;

public abstract class MenuController implements Controller {
    protected Menu model;
    protected MenuViewer view;
    protected StateRecognizer recognizer;

    public MenuController(Menu model, MenuViewer view, StateRecognizer recognizer){
        this.model = model;
        this.view = view;
        this.recognizer = recognizer;
    }

    @Override
    public void start() throws IOException {
        LevelViewer.COMMAND command = view.getNextCommand();

        if (command == DOWN)
            moveDown();
        else if (command == UP)
            moveUp();
        else if (command == SELECT)
            recognizer.getCurrentState().advance();
        else if (command == QUIT)
            model.setFinished(true);
    }

    public abstract void moveDown();
    public abstract void moveUp();
}
