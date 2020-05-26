package com.g13.view.menus;

import com.g13.model.menus.Start;
import com.g13.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class StartViewer extends TitlesViewer implements View {
    private Start model;

    public StartViewer(Start model, TerminalScreen screen) {
        super(model, screen);
        this.model = model;
    }

    @Override
    public void draw() throws IOException {
        screen.clear();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        drawStartComponents();
        drawTitle();
        screen.refresh();
    }

    private void drawStartComponents(){
        for (int i = 0; i < model.getButtons().size();  i++)
            drawButton(model.getButtons().get(i), model.getSelection() == i, graphics);
    }

    public void drawInstructions() throws IOException {
        screen.clear();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        drawTitle();
        screen.refresh();
    }
}
