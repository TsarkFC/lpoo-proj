package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;

public class SelectCard implements Command{
    private ArenaController controller;
    private int selection;

    public SelectCard(ArenaController controller, int selection){
        this.controller = controller;
        this.selection = selection;
    }

    @Override
    public void execute() {
        controller.getPlayerController().setCardSelected(selection);
    }
}
