package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;

import static java.lang.Integer.min;

public class InterstageHandler implements Command{
    private ArenaController controller;

    public InterstageHandler(ArenaController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (controller.getCmdStage() == 0){
            int min = min(controller.getPlayerController().getPoints(), controller.getEnemyController().getPoints());
            controller.getPlayerController().subtractPoints(min);
            controller.getEnemyController().subtractPoints(min);
            controller.advanceCmdStage();
        }
        else if (controller.getCmdStage() == 1){
            controller.getLoser().subtractHealth(controller.getWinner().getPoints());
            controller.advanceCmdStage();
            controller.resetRound();
        }
    }
}
