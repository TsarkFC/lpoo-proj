package com.g13.model.menus.button;

public class Button {
    private int x;
    private int y;
    private String message;

    public Button(int x, int y, String message){
        this.x = x;
        this.y = y;
        this.message = message;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public String getMessage() { return message; }
}
