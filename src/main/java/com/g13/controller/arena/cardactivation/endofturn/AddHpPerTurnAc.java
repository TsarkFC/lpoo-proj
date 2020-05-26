package com.g13.controller.arena.cardactivation.endofturn;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.SpecialCardAc;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;

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

            AddHpPerTurn c = card;

            a.add(c);
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
