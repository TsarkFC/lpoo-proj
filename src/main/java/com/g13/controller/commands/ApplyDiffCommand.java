package com.g13.controller.commands;

import com.g13.controller.ArenaController;

public class ApplyDiffCommand implements  Command{
    private ArenaController controller;

    public ApplyDiffCommand(ArenaController controller){
        this.controller = controller;
    }

    public void execute(){
        controller.getLooser().subtractHealth(controller.getWinner().getPoints());
        controller.getLooser().zeroHealth();
    }
}
