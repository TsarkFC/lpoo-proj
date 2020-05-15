package com.g13.model.specialcards.instant;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.specialcards.SpecialCard;

import java.util.concurrent.ThreadLocalRandom;

public class FluxModifierAtoB extends SpecialCard {

    private int minModNum;
    private int maxModNum;

    public FluxModifierAtoB(int cost, char symbol, String cardInfo, int minModNum, int maxModNum) {
        super(cost, symbol, cardInfo);
        this.minModNum = minModNum;
        this.maxModNum = maxModNum;
        cardType = SpecialCard.CARD_TYPE.FLUX_MODIFIER_A_TO_B;
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController){
        super.checkEnemyPlay(arenaController);

        if(arenaController.getEnemy().getPoints() + minModNum > arenaController.getEnemy().getMaxPoints())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckFluxModifier(arenaController, getCost(), minModNum, maxModNum))
            return false;

        activate(ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //Add a random number --Has to be in the function itself, if it were in the constructor, it'd always add the same number
        int modNum = ThreadLocalRandom.current().nextInt(minModNum, maxModNum + 1);

        //When the card is played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());
            currentController.setPoints(currentController.getPoints() + modNum);
            arenaController.checkControllerPoints();
        }
    }

    public int getMinModNum(){ return minModNum; }
    public int getMaxModNum(){ return maxModNum; }
}
