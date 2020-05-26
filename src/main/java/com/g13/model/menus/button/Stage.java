package com.g13.model.menus.button;

import com.g13.model.menus.button.Button;

public class Stage extends Button {
    private boolean unlocked;

    public Stage(int x, int y, boolean unlocked){
        super(x, y, " ");
        this.unlocked = unlocked;
    }

    public boolean isUnlocked() { return unlocked; }
    public void setUnlocked(boolean unlocked) { this.unlocked = unlocked; }
}
