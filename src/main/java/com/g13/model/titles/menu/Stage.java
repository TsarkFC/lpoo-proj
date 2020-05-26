package com.g13.model.titles.menu;

import com.g13.model.titles.start.Button;

public class Stage {

    private int x;
    private int y;
    private boolean unlocked;

    public Stage(int x, int y, boolean unlocked){
        this.x = x;
        this.y = y;
        this.unlocked = unlocked;
    }

    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }

    public int getX() { return x; }
    public int getY() { return y; }
}
