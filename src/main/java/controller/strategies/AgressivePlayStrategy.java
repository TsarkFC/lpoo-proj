package controller.strategies;

import controller.ArenaController;
import controller.commands.DrawCardCommand;

public class AgressivePlayStrategy implements PlayStrategy{

    public AgressivePlayStrategy(){
    }

    @Override
    public boolean playTurn(ArenaController arenaController){
        boolean has_drawn = false;

        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMax_points() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        }

        //Condição de jogar modifiers


        //Fazer draw [2]?
        if(arenaController.getEnemy().getPoints() <= arenaController.getPlayer().getPoints() && !has_drawn){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        }




        return has_drawn;
    }

}
