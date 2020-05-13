package com.g13.controller.strategies;

import com.g13.controller.commands.DrawCardCommand;
import com.g13.controller.ArenaController;
import com.g13.model.SpecialCardTypes.SpecialCard;

import java.util.List;


public class NormalPlayStrategy implements PlayStrategy{

    protected int mana_saved = 5;

    //TODO: Create function to calculate hipothetical damage taken by the enemy when they lose

    @Override
    public boolean playTurn(ArenaController arenaController) {

        mana_saved = 5;

        boolean draw_limit_reached = false;

        boolean has_drawn = false;



        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
        }

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemy().getPoints() >= arenaController.getEnemy().getMaxPoints() - 4){
            draw_limit_reached = true;
        }

        if(arenaController.getEnemy().getPoints() <= arenaController.getPlayer().getPoints() && draw_limit_reached){
            mana_saved = 0;
            for(int i = 0; i < 4; i++){
                if(arenaController.getEnemy().getPlayDeck().get(i).checkEnemyPlay(arenaController))
                    break;
            }
        }

        //Don't wanna draw but you're losing against the player?
        //if(arenaController.getEnemy().getPoints() < arenaController.getPlayer().getPoints() && draw_limit_reached){
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
        //if(arenaController.getEnemy().getPoints() > arenaController.getPlayer().getPoints() && draw_limit_reached){
            //Play defensive card if
            //
        //}
        return has_drawn;
    }

    @Override
    public boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum) {
        return mana_saved <= arenaController.getEnemy().getMana() - cost;
    }


}
