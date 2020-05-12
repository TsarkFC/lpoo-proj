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
        if(arenaController.getEnemy().getMana() < getCost()){
            return false;
        }

        if(arenaController.getEnemy().getPoints() + modNum > arenaController.getEnemy().getMaxPoints()){
            return false;
        }



        if(!arenaController.getEnemy().getPlayStrategy().CheckStaticModifier(arenaController, getCost(), modNum)){
            return false;
        }

        activate(ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController;

        //Find who is playing now
        if(arenaController.getModel().getPlayersTurn()){
            currentController = arenaController.getPlayerController();
        }
        else{
            currentController = arenaController.getEnemyController();
        }

        //Subtract mana
        currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }

}
