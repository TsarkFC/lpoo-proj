package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.commands.DrawCardCommand;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.StaticModifier;

public class AggressivePlayStrategy extends PlayStrategy{

    @Override
    public boolean playTurn(ArenaController arenaController){

        mana_saved = 0;

        boolean has_drawn = false;
        boolean draw_limit_reached = false;

        flux_percentage_accept = 50;

        //Fazer draw?
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 4){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
            draw_limit_reached = true;
        }

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemy().getPoints() >= arenaController.getEnemy().getMaxPoints() - 4){
            draw_limit_reached = true;
        }

        for(int i = 0; i < 4; i++){
            SpecialCard card = arenaController.getEnemyController().getCard(i);
            if(card instanceof StaticModifier || card instanceof FluxModifierAtoB)
                arenaController.getActivationFactory().getActivation(card).checkEnemyPlay(arenaController);
        }

        //Fazer draw [2]?
        if(arenaController.getEnemyController().getPoints() <= arenaController.getPlayerController().getPoints() && !has_drawn){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
        }

        //Vais querer fazer draw na próxima ronda?
        if(arenaController.getEnemyController().getPoints() >= arenaController.getEnemyController().getMaxPoints() - 4){
            draw_limit_reached = true;
        }

        //Heal (End turn)
        health_to_heal = 5;
        for(int i = 0; i < 4; i++){
            SpecialCard card = arenaController.getEnemyController().getCard(i);
            if(card instanceof AddHpPerTurn && arenaController.getEnemyController().getHealth() < 3)
                arenaController.getActivationFactory().getActivation(card).checkEnemyPlay(arenaController);
        }

        return has_drawn;
    }
}
