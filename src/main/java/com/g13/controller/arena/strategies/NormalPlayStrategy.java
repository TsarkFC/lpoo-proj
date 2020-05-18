package com.g13.controller.arena.strategies;

import com.g13.controller.arena.commands.DrawCardCommand;
import com.g13.controller.arena.ArenaController;
import com.g13.model.arena.specialcards.SpecialCard;


public class NormalPlayStrategy extends PlayStrategy{


    //TODO: Create function to calculate hipothetical damage taken by the enemy when they lose

    @Override
    public boolean playTurn(ArenaController arenaController) {

        mana_saved = 0;
        flux_percentage_accept = 0.75;

        boolean draw_limit_reached = false;

        boolean has_drawn = false;



        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
        }

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemyController().getPoints() >= arenaController.getEnemyController().getMaxPoints() - 4){
            draw_limit_reached = true;
        }

        for(int i = 0; i < 4; i++){
            if(arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.STATIC_MODIFIER
                || arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.FLUX_MODIFIER_A_TO_B
                || arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.FLUX_MODIFIER_X_Y_OR_Z)
                arenaController.getActivationFactory().getActivation(arenaController.getEnemy().getPlayDeck().get(i))
                        .checkEnemyPlay(arenaController);
        }


        //Heal (Instant)
        health_to_heal = 2;
        for(int i = 0; i < 4; i++){
            if(arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.HEAL_INSTANT)
                arenaController.getActivationFactory().getActivation(arenaController.getEnemy().getPlayDeck().get(i))
                        .checkEnemyPlay(arenaController);
        }

        //Heal (End turn)
        health_to_heal = 5;
        for(int i = 0; i < 4; i++){
            if(arenaController.getEnemy().getPlayDeck().get(i).getCardType() == SpecialCard.CARD_TYPE.HEAL_ON_END_TURN)
                arenaController.getActivationFactory().getActivation(arenaController.getEnemy().getPlayDeck().get(i))
                    .checkEnemyPlay(arenaController);
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


}
