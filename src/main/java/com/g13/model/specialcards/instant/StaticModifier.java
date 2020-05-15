package com.g13.model.specialcards.instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.specialcards.SpecialCard;

public class StaticModifier extends SpecialCard {

    private int modNum;

    public StaticModifier(int cost, char symbol, String cardInfo, int modNum) {
        super(cost, symbol, cardInfo);
        this.modNum = modNum;
        cardType = CARD_TYPE.STATIC_MODIFIER;
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController){
        super.checkEnemyPlay(arenaController);

        if(arenaController.getEnemy().getPoints() + modNum > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckStaticModifier(arenaController, getCost(), modNum))
            return false;

        activate(ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }

    public int getModNum(){ return modNum; }
}
