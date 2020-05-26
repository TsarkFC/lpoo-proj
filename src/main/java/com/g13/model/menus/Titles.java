package com.g13.model.menus;

import com.g13.model.Model;

public abstract class Titles implements Model {
    protected boolean isFinished;
    protected final char[][] title_void = {{'*',' ','*',' ','*','*','*',' ','*','*','*',' ','*','*',' '},
            {'*',' ','*',' ','*',' ','*',' ',' ','*',' ',' ','*',' ','*'},
            {' ','*',' ',' ','*','*','*',' ','*','*','*',' ','*','*',' '}};
    protected final char[][] title_tyrant = {{'*','*','*',' ','*',' ','*',' ','*','*',' ',' ',' ','*',' ',' ','*','*',' ',' ','*','*','*'},
            {' ','*',' ',' ',' ','*',' ',' ','*','*',' ',' ','*','*','*',' ','*',' ','*',' ',' ','*',' '},
            {' ','*',' ',' ',' ','*',' ',' ','*',' ','*',' ','*',' ','*',' ','*',' ','*',' ',' ','*',' '}};

    public char[][] getTitle_void() { return title_void; }
    public char[][] getTitle_tyrant() { return title_tyrant; }

    public Titles(boolean isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public boolean isFinished() { return isFinished; }
    public void setFinished(boolean isFinished) { this.isFinished = isFinished; }
}
