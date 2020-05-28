package com.g13.controller.arena.strategies;

import com.g13.controller.arena.commands.DrawCardCommand;
import com.g13.controller.arena.ArenaController;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.StaticModifier;


public class NormalPlayStrategy extends PlayStrategy{


    //TODO: Create function to calculate hipothetical damage taken by the enemy when they lose

    @Override
    public boolean playTurn(ArenaController arenaController) {

        mana_saved = 0;
        flux_percentage_accept = 0.75;

        boolean draw_limit_reached = false;

        boolean has_drawn = false;

        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 5){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
        }

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemyController().getPoints() >= arenaController.getEnemyController().getMaxPoints() - 5){
            draw_limit_reached = true;
        }

        for(int i = 0; i < 4; i++){
            SpecialCard card = arenaController.getEnemyController().getCard(i);
            if(card instanceof StaticModifier || card instanceof FluxModifierAtoB)
                arenaController.getActivationFactory().getActivation(card).checkEnemyPlay(arenaController);
        }

        //Heal (End turn)
        health_to_heal = 5;
        for(int i = 0; i < 4; i++){
            SpecialCard card = arenaController.getEnemyController().getCard(i);
            if(card instanceof AddHpPerTurn && arenaController.getEnemyController().getHealth() < 5)
                arenaController.getActivationFactory().getActivation(card).checkEnemyPlay(arenaController);
        }


        //Don't wanna draw but you're losing against the player?
        if(arenaController.getEnemy().getPoints() < arenaController.getPlayer().getPoints() && draw_limit_reached){
            for (int i = 0; i < 4; i++){
                SpecialCard card = arenaController.getEnemyController().getCard(i);
                int increment = 0;
                if(card instanceof StaticModifier){
                    StaticModifier mod = (StaticModifier) card;
                    increment = mod.getModNum();
                }
                else if (card instanceof FluxModifierAtoB){
                    FluxModifierAtoB mod = (FluxModifierAtoB) card;
                    increment = mod.getMaxModNum();
                }
                if (increment != 0){
                    int future_score = increment + arenaController.getEnemyController().getPoints();
                    if (future_score <= arenaController.getEnemyController().getMaxPoints() &&
                            future_score >= arenaController.getPlayerController().getPoints()){
                        arenaController.getActivationFactory().getActivation(card).checkEnemyPlay(arenaController);
                    }
                }
            }
            /*
            Play special card if:
                -The card is a modifier. (check all) [CHECKED]
                -You don't wanna draw anymore. [CHECKED]
                -You don't overdraw from playing it. [CHECKED]
                -You have enough mana for it. [CHECKED]
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
                -The opponent has more points than you. [CHECKED]
                AND
                -You will have at least the same points as the player. [CHECKED]
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

        }

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


}
