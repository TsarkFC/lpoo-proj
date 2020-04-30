package controller.strategies;

import controller.commands.DrawCardCommand;
import controller.ArenaController;


public class CarefulPlayStrategy implements PlayStrategy{


    public CarefulPlayStrategy(){
    }


    @Override
    public void playTurn(ArenaController arenaController) {
        if(arenaController.getEnemy().getPoints() < 3){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
        }
    }
}
