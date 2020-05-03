package com.g13.controller.strategies;

import com.g13.controller.ArenaController;
import com.g13.controller.commands.DrawCardCommand;

public class AggressivePlayStrategy implements PlayStrategy{

    public AggressivePlayStrategy(){
    }

    @Override
    public boolean playTurn(ArenaController arenaController){
        boolean has_drawn = false;

        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMax_points() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        }

        //Condição de jogar modifiers


        //Fazer draw [2]?
        if(arenaController.getEnemy().getPoints() <= arenaController.getPlayer().getPoints() && !has_drawn){
            DrawCardCommand command = new DrawCardCommand(arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        }




        return has_drawn;
    }

}
