package com.g13.model.menu;

import com.g13.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Model {
    private boolean isFinished;
    private List<Stage> stages;
    private int cross;

    public Menu(){
        isFinished = false;
        stages = new ArrayList<>();
        stages.add(new Stage(0, 20, true));
        stages.add(new Stage(10, 20, true));
        stages.add(new Stage(30, 20, false));
        stages.add(new Stage(40, 20, false));
        stages.add(new Stage(50, 20, false));
        cross = 0;
    }

    @Override
    public boolean isFinished() { return isFinished; }
    public void setFinished(boolean isFinished) { this.isFinished = isFinished; }

    public void crossMoveRight() {cross++;}
    public void crossMoveLeft() {cross--;}
    public int getCross() { return cross; }

    public List<Stage> getStages() { return stages; }
    public boolean getStagesAtCross() { return stages.get(cross).isUnlocked(); }
}
