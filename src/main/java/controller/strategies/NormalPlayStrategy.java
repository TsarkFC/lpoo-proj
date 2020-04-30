package controller.strategies;

import controller.commands.DrawCardCommand;
import controller.ArenaController;


public class NormalPlayStrategy implements PlayStrategy{


    public NormalPlayStrategy(){
    }


    @Override
    public boolean playTurn(ArenaController arenaController) {
        boolean draw_limit_reached = false;

        boolean has_drawn = false;

        //Fazer draw?
        //if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMax_points() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController, arenaController.getEnemyController(), arenaController.getPlayerController());
            command.execute();
            has_drawn = true;
        //}

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMax_points() - 4){
            draw_limit_reached = true;
        }

        //Don't wanna draw but you're losing against the player?
        //if(arenaController.getEnemy().getPoints() < arenaController.getPlayer().getPoints() && limit_reached){
            /*
            Play special card if:
                -The card is a modifier. (check all)
                -You don't wanna draw anymore.
                -You don't overdraw from playing it.
                -You have enough mana for it.
                (
                    (
                    IF careful
                    AND
                    -You have enough mana +5 for careful?
                    )
                    OR
                    (
                    NOT careful
                    )
                AND
                -The opponent has more points than you.
                AND
                -You will have at least the same points as the player.
                )
                OR
                (
                -You will die from the player the damage he'd do this turn.
                )

                OR
                (
                    -(IF agressive)
                    AND
                    -You are tied with your opponent
                )
             */

        //}



        //Winning and don't wanna draw? Play a win-more card!
        //if(arenaController.getEnemy().getPoints() > arenaController.getPlayer().getPoints() && draw_limit_reached){
            //Play win more card
            //Careful only plays a win more card if the player has locked in too
        //}

        //Losing and don't wanna draw and can't win? Play a defensive card!
        if(arenaController.getEnemy().getPoints() > arenaController.getPlayer().getPoints() && draw_limit_reached){
            //Play defensive card if
            //
        }



        return has_drawn;
    }
}
