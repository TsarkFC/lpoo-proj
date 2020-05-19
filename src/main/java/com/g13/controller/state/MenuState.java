package com.g13.controller.state;

import com.g13.controller.Controller;
import com.g13.controller.menu.MenuController;
import com.g13.model.Model;
import com.g13.model.menu.Menu;
import com.g13.view.View;
import com.g13.view.menu.MenuViewer;

import java.io.IOException;

public class MenuState implements State{
    private Menu menu;
    private MenuViewer menuViewer;
    private MenuController menuController;
    private StateRecognizer recognizer;

    public MenuState(StateRecognizer recognizer) throws IOException {
        menu = new Menu();
        menuViewer = new MenuViewer(menu);
        menuController = new MenuController(menu, menuViewer);
        this.recognizer = recognizer;
    }

    @Override
    public Model getModel() { return menu; }

    @Override
    public View getView() { return menuViewer; }

    @Override
    public Controller getController() {
        return menuController;
    }

    @Override
    public void advance() { recognizer.setCurrentState(recognizer.getGameState());}
}
