package com.g13.model.specialcards.endofturn;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.specialcards.SpecialCard;

import java.util.List;

public class AddHpPerTurn extends SpecialCard {

    private int HPPerTurn;

    public AddHpPerTurn(int cost, char symbol, String cardInfo, int HPPerTurn, int numRounds) {
        super(cost, symbol, cardInfo);
        this.HPPerTurn = HPPerTurn;
        this.roundsLeft = numRounds;
        cardType = SpecialCard.CARD_TYPE.HEAL_ON_END_TURN;
    }

    public void activate(ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //When it's played
        if(condition == ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana on play
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - getCost());
            List<SpecialCard> a = currentController.getParticipant().getActiveCards();
            a.add(this);
            currentController.getParticipant().setActiveCards(a);
        }

        //Happens at the end of each round until it's over
        else if(condition == ACTIVATION_CONDITIONS.ON_END_TURN){
            currentController.getParticipant().setHealth(currentController.getParticipant().getHealth() + HPPerTurn);
            if(currentController.getParticipant().getHealth() > currentController.getParticipant().getMaxHealth()){
                currentController.getParticipant().setHealth(currentController.getParticipant().getMaxHealth());
            }
            roundsLeft--;
        }
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController) {
        super.checkEnemyPlay(arenaController);

        if(arenaController.getEnemy().getHealth() >= arenaController.getEnemy().getMaxHealth())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckOverTimeHeal(arenaController, getCost()))
            return false;

        activate(ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }

    public int getHPPerTurn(){ return HPPerTurn; }

}
