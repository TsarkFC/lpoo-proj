package controller.strategies;

import controller.commands.DrawCardCommand;
import controller.ArenaController;


public class NormalPlayStrategy implements PlayStrategy{


    public NormalPlayStrategy(){
    }


    @Override
    public void playTurn(ArenaController arenaController) {
        if(arenaController.getEnemy().getPoints() < 8){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
        }
    }
}
