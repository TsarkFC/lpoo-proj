package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;

public class QuitCommand implements Command{
    private ArenaController controller;

    public QuitCommand(ArenaController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getModel().finish();
    }
}
