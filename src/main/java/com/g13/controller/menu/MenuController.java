package com.g13.controller.menu;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.menu.Menu;
import com.g13.view.menu.MenuViewer;

import java.io.IOException;

import static com.g13.view.menu.MenuViewer.COMMAND.*;

public class MenuController implements Controller {
    private Menu model;
    private MenuViewer view;
    private StateRecognizer recognizer;

    public MenuController(Menu model, MenuViewer view, StateRecognizer recognizer){
        this.model = model;
        this.view = view;
        this.recognizer = recognizer;
    }

    @Override
    public void start() throws IOException {
        MenuViewer.COMMAND command = view.getNextCommand();

        if (command == DOWN) moveDown();
        else if (command == UP) moveUp();
        else if (command == SELECT) {
            processSelection();
            return;
        }
        else if (command == QUIT) model.setFinished(true);
        view.draw();
    }

    private void moveDown(){
        if (model.getCross() < 4 && model.getNextCross()) model.crossMoveDown();
    }
    private void moveUp() { if (model.getCross() > 0) model.crossMoveUp(); }

    private void processSelection() throws IOException {
        recognizer.getCurrentState().advance();
    }
}
