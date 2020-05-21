package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;
import static java.lang.Integer.min;

public class PointDiffCommand implements Command{
    private ArenaController controller;

    public PointDiffCommand(ArenaController controller){
        this.controller = controller;
    }

    public void execute(){
        int min = min(controller.getPlayerController().getPoints(), controller.getEnemyController().getPoints());
        controller.getPlayerController().subtractPoints(min);
        controller.getEnemyController().subtractPoints(min);
    }

}
