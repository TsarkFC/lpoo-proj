package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;

public class ApplyDiffCommand implements  Command{
    private ArenaController controller;

    public ApplyDiffCommand(ArenaController controller){
        this.controller = controller;
    }

    public void execute(){
        controller.getLoser().subtractHealth(controller.getWinner().getPoints());
        controller.getLoser().zeroHealth();
    }
}
