package com.g13.controller.arena.commands;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.strategies.PlayStrategy;

public class PlayEnemyTurn implements Command{
    private ArenaController controller;

    public PlayEnemyTurn(ArenaController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.getModel().setPlayersTurn(false);
        PlayStrategy strategy = controller.getEnemy().getPlayStrategy();
        if (!strategy.playTurn(controller))
            new SkipTurnCommand(controller.getEnemyController()).execute();
        controller.getModel().setPlayersTurn(true);
    }
}
