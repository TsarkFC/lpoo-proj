package com.g13.controller.cardactivation.endofturn;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.controller.cardactivation.SpecialCardAc;
import com.g13.model.specialcards.SpecialCard;
import com.g13.model.specialcards.endofturn.AddHpPerTurn;

import java.util.List;

public class AddHpPerTurnAc extends SpecialCardAc {
    private AddHpPerTurn card;

    public AddHpPerTurnAc(AddHpPerTurn card){
        super(card);
        this.card = card;
    }

    public void activate(SpecialCard.ACTIVATION_CONDITIONS condition, ArenaController arenaController){

        ParticipantController currentController = arenaController.getCurrent();

        //When it's played
        if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY){
            //Subtract mana on play
            currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
            List<SpecialCard> a = currentController.getParticipant().getActiveCards();
            a.add(card);
            currentController.getParticipant().setActiveCards(a);
        }

        //Happens at the end of each round until it's over
        else if(condition == SpecialCard.ACTIVATION_CONDITIONS.ON_END_TURN){
            currentController.getParticipant().setHealth(currentController.getParticipant().getHealth() + card.getHPPerTurn());
            if(currentController.getParticipant().getHealth() > currentController.getParticipant().getMaxHealth()){
                currentController.getParticipant().setHealth(currentController.getParticipant().getMaxHealth());
            }
            card.decrementRoundsLeft();
        }
    }

    public boolean checkEnemyPlay(ArenaController arenaController) {
        checkPlay(arenaController);

        if(arenaController.getEnemy().getHealth() >= arenaController.getEnemy().getMaxHealth())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckOverTimeHeal(arenaController, card.getCost()))
            return false;

        activate(SpecialCard.ACTIVATION_CONDITIONS.ON_PLAY, arenaController);
        return true;
    }
}
