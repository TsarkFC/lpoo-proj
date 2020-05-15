package com.g13.controller.strategies;

import com.g13.controller.ArenaController;
import com.g13.controller.commands.DrawCardCommand;
import com.g13.model.specialcards.SpecialCard;

public class AggressivePlayStrategy extends PlayStrategy{

    public AggressivePlayStrategy(){
    }

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
            if(arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.STATIC_MODIFIER
                || arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.FLUX_MODIFIER_A_TO_B
                || arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.FLUX_MODIFIER_X_Y_OR_Z)
                arenaController.getEnemy().getPlayDeck().get(i).checkEnemyPlay(arenaController);
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

        //Heal (Instant)
        health_to_heal = 2;
        for(int i = 0; i < 4; i++){
            if(arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.HEAL_INSTANT)
                arenaController.getEnemy().getPlayDeck().get(i).checkEnemyPlay(arenaController);
        }
        //Heal (End turn)
        health_to_heal = 5;
        for(int i = 0; i < 4; i++){
            if(arenaController.getEnemyController().getCardType(i) == SpecialCard.CARD_TYPE.HEAL_ON_END_TURN)
                arenaController.getEnemy().getPlayDeck().get(i).checkEnemyPlay(arenaController);
        }

        return has_drawn;
    }

    @Override
    public boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum) {
        return true;
    }

}
