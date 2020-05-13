package com.g13.model.SpecialCardTypes.EndOfTurn;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCardTypes.SpecialCard;

import java.util.List;

public class AddHpPerTurn extends SpecialCard {

    int HPPerTurn;
    int numRounds;

    final CARD_TYPE cardType = SpecialCard.CARD_TYPE.HEAL_ON_END_TURN;

    public AddHpPerTurn(int cost, char symbol, String cardInfo, int HPPerTurn, int numRounds) {
        super(cost, symbol, cardInfo);
        this.HPPerTurn = HPPerTurn;
        this.numRounds = numRounds;
        this.roundsLeft = numRounds;
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

}
