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
        this.recognizer = recognizer;
        menu = new Menu();
        menuViewer = new MenuViewer(menu, recognizer.getScreen());
        menu.setObserver(menuViewer);
        menuController = new MenuController(menu, menuViewer, recognizer);
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
    public void advance() throws IOException {
        recognizer.setCurrentState(recognizer.getGameState());
        recognizer.getCurrentState().getView().draw(); //change to notify
    }
}
