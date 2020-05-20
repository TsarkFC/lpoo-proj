package com.g13.view;

import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public interface View<C extends  Enum<C>> {
    void draw() throws IOException;
    C getNextCommand() throws IOException;
}
