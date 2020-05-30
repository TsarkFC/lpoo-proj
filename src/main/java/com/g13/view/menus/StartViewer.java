package com.g13.view.menus;

import com.g13.model.menus.Start;
import com.g13.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class StartViewer extends MenuViewer implements View {
    private Start model;
    private int line = 0;
    private final String[][] instructions = {
            {" Welcome to Void Tyrant! Here you will play", "against three enemies trying to defeat every ",
                    "single one of them.",
                    " Every time you lose you will return",  "to the beginning."},
            { " Firstly you have a draw deck and a play deck.","The draw deck contains 24 cards whose values go","from 1 to 6.",
                    " Your objective is to continuously draw card","until you get as close as possible to 12.",
            " Drawing from draw deck is performed by ","pressing the 'd' key"},
            {" Four cards will be displayed from your ","play deck and which you can select on by","pressing the {1,2,3,4} number keys.",
                    "To activate a selected card press TAB."},
            {" Press ENTER to finish your turn. If enemy has ","not finished yet keep pressing 'd' until it","does."},
            {" Once both players have finished press ENTER","two more times to perform end of turn actions."},
            {" When both players are on drawing state again","you repeat the process until the game ends."}
    };

    public StartViewer(Start model, TerminalScreen screen) {
        super(model, screen);
        this.model = model;
    }

    @Override
    public void draw() throws IOException {
        screen.clear();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        if (model.getSelection() == -1)
            drawInstructions();
        else {
            drawStartComponents();
            drawTitle();
        }
        screen.refresh();
    }

    private void drawStartComponents(){
        for (int i = 0; i < model.getButtons().size();  i++)
            drawButton(model.getButtons().get(i), model.getSelection() == i, graphics);
    }

    private void drawInstructions() throws IOException {
        screen.clear();
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(50, 30), ' ');
        for (int i = 0; i < instructions.length; i++) {
            line++;
            drawInstruction(0, line, instructions[i]);
        }
        line = 0;
        screen.refresh();
    }

    private void drawInstruction(int x, int y, String[] instruction){
        brownGraphics.putString(x, y, " ");
        for (int i = 0; i < instruction.length; i++){
            graphics.putString(x+2, line, instruction[i]);
            line++;
        }
    }
}
