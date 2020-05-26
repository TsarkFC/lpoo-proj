package com.g13.model.menus;

import com.g13.model.Model;
import com.g13.model.menus.button.Stage;

import java.util.ArrayList;
import java.util.List;

public class Level extends Titles implements Model {
    private List<Stage> stages;
    private int cross;
    private int x = 12;

    public Level(){
        super(false);
        stages = new ArrayList<>();
        stages.add(new Stage(x, 12, true));
        stages.add(new Stage(x, 17, true));
        stages.add(new Stage(x, 22, false));
        stages.add(new Stage(x, 27, false));
        cross = 0;
    }

    public void crossMoveDown() {cross++;}
    public void crossMoveUp() {cross--;}
    public int getCross() { return cross; }
    public int getX() { return x; }

    public List<Stage> getStages() { return stages; }
    public boolean getNextCross() { return stages.get(cross+1).isUnlocked(); }

}
