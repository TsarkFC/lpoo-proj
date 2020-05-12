package com.g13.controller.strategies;

import com.g13.controller.ArenaController;
import com.g13.controller.commands.DrawCardCommand;


public class CarefulPlayStrategy implements PlayStrategy{

    @Override
    public boolean playTurn(ArenaController arenaController) {
        boolean has_drawn = false;
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 6){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(),
                                                                arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        }


        return has_drawn;
    }
}
