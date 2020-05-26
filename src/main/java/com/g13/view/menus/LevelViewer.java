package com.g13.view.menus;

import com.g13.model.menus.Level;
import com.g13.model.menus.button.Stage;
import com.g13.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class LevelViewer extends TitlesViewer implements View {
    private Level model;
    private TerminalScreen screen;
    private final String[] menu_titles = {"[Previous menu]",
                                        "[Enemy 1] Normal mentality",
                                        "[Enemy 2] Passive mentality",
                                        "[Enemy 3] Aggressive mentality"};

    public LevelViewer(Level model, TerminalScreen screen) {
        super(model,screen);
        this.model = model;
        this.screen = screen;
    }

    @Override
    public void draw() throws IOException {
        screen.clear();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        drawMenuComponents();
        drawTitle();
        screen.refresh();
    }

    private void drawMenuComponents(){
        Stage stage;
        for (int i = 0; i < model.getStages().size(); i++){
            stage = model.getStages().get(i);
            drawButton(stage, stage.isUnlocked(), redGraphics);
            graphics.putString(stage.getX() + 2, stage.getY(), menu_titles[i]);
        }
        brownGraphics.putString(model.getX(), (model.getCross()+1)*5+7, "x");
    }
}
