package com.g13.controller.menu;

import com.g13.controller.Controller;
import com.g13.model.menu.Menu;
import com.g13.view.menu.MenuViewer;

import java.io.IOException;

import static com.g13.view.menu.MenuViewer.COMMAND.*;

public class MenuController implements Controller {
    private Menu model;
    private MenuViewer view;

    public MenuController(Menu model, MenuViewer view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() throws IOException {
        MenuViewer.COMMAND command = view.getNextCommand();

        if (command == RIGHT) moveRight();
        else if (command == LEFT) moveLeft();
        else if (command == SELECT) processSelection();
    }

    private void moveRight(){
        if (model.getCross() < 5 && model.getStagesAtCross()) model.crossMoveRight();
    }

    private void moveLeft() {
        if (model.getCross() > 0) model.crossMoveRight();

    }

    private void processSelection() {

    }
}
