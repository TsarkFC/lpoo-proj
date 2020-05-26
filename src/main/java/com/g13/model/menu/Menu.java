package com.g13.model.menu;

import com.g13.model.Model;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Model {
    private boolean isFinished;
    private List<Stage> stages;
    private int cross;
    private int x = 12;
    private final char[][] title_void = {{'*',' ','*',' ','*','*','*',' ','*','*','*',' ','*','*',' '},
                                         {'*',' ','*',' ','*',' ','*',' ',' ','*',' ',' ','*',' ','*'},
                                         {' ','*',' ',' ','*','*','*',' ','*','*','*',' ','*','*',' '}};
    private final char[][] title_tyrant = {{'*','*','*',' ','*',' ','*',' ','*','*',' ',' ',' ','*',' ',' ','*','*',' ',' ','*','*','*'},
                                           {' ','*',' ',' ',' ','*',' ',' ','*','*',' ',' ','*','*','*',' ','*',' ','*',' ',' ','*',' '},
                                           {' ','*',' ',' ',' ','*',' ',' ','*',' ','*',' ','*',' ','*',' ','*',' ','*',' ',' ','*',' '}};

    public Menu(){
        isFinished = false;
        stages = new ArrayList<>();
        stages.add(new Stage(x, 12, true));
        stages.add(new Stage(x, 17, false));
        stages.add(new Stage(x, 22, false));
        cross = 0;
    }

    @Override
    public boolean isFinished() { return isFinished; }
    public void setFinished(boolean isFinished) { this.isFinished = isFinished; }

    public void crossMoveDown() {cross++;}
    public void crossMoveUp() {cross--;}
    public int getCross() { return cross; }
    public int getX() { return x; }

    public List<Stage> getStages() { return stages; }
    public boolean getNextCross() { return stages.get(cross+1).isUnlocked(); }

    public char[][] getTitle_void() { return title_void; }

    public char[][] getTitle_tyrant() { return title_tyrant; }
}
