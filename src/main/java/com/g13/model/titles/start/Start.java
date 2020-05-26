package com.g13.model.titles.start;

import com.g13.model.Model;
import com.g13.model.titles.Titles;

import java.util.ArrayList;
import java.util.List;

public class Start extends Titles implements Model {
    private List<Button> buttons = new ArrayList<>();
    private int selection = 0;

    public Start(){
        super(false);
        Button play = new Button(20, 13, "Play game");
        buttons.add(play);
        Button info = new Button(19, 17, "Instructions");
        buttons.add(info);
    }


    public List<Button> getButtons() { return buttons; }

    public int getSelection() { return selection; }
    public void setSelection(int selection) { this.selection = selection; }

    public void selectionMoveUp() {selection--;}
    public void selectionMoveDown() {selection++;}
}
