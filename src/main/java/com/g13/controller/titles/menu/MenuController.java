package com.g13.controller.titles.menu;

import com.g13.controller.Controller;
import com.g13.controller.state.StateRecognizer;
import com.g13.controller.titles.TitlesController;
import com.g13.model.titles.menu.Menu;
import com.g13.view.titles.menu.MenuViewer;

public class MenuController extends TitlesController implements Controller {
    private Menu model;

    public MenuController(Menu model, MenuViewer view, StateRecognizer recognizer){
        super(model, view, recognizer);
        this.model = model;
    }

    @Override
    public void moveDown(){ if (model.getCross() < 3 && model.getNextCross()) model.crossMoveDown(); }
    @Override
    public void moveUp() { if (model.getCross() > 0) model.crossMoveUp(); }
}
