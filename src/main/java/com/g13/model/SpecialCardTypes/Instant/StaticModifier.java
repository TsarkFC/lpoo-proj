package com.g13.model.SpecialCardTypes.Instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.Arena;
import com.g13.model.SpecialCardTypes.SpecialCard;

public class StaticModifier extends SpecialCard {

    int modNum;

    final CARD_TYPE cardType = SpecialCard.CARD_TYPE.STATIC_MODIFIER;

    public StaticModifier(int cost, char symbol, String cardInfo, int modNum) {
        super(cost, symbol, cardInfo);
        this.modNum = modNum;
    }

    public boolean checkEnemyPlay(ArenaController arenaController){
        System.out.println("Reached correct checkEnemyPlay");
        if(arenaController.getEnemy().getMana() < getCost()){
            return false;
        }

        System.out.println("Condition 1 passed");
        if(arenaController.getEnemy().getPoints() + modNum > arenaController.getEnemy().getMaxPoints()){
            return false;
        }



        System.out.println("Condition 2 passed");
        if(!arenaController.getEnemy().getPlayStrategy().CheckStaticModifier(arenaController, getCost(), modNum)){
            return false;
        }

        System.out.println("Conditions passed");
        activate(ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController;

        if(arenaController.getModel().getPlayersTurn()){
            currentController = arenaController.getPlayerController();
        }
        else{
            currentController = arenaController.getEnemyController();
        }

        currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());

        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }

}
