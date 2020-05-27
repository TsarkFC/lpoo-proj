package com.g13.controller.arena.cardactivation.endofturn;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;

import java.util.List;

public class AcAddHpPerTurn extends AcSpecialCard implements EndOfTurn{
    private AddHpPerTurn card;

    public AcAddHpPerTurn(AddHpPerTurn card){
        super(card);
        this.card = card;
    }

    @Override
    public void activate(ArenaController arenaController){
        ParticipantController currentController = arenaController.getCurrent();
        //Subtract mana on play
        currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
        List<SpecialCard> a = currentController.getParticipant().getActiveCards();

        AddHpPerTurn c = card;

        a.add(c);
        currentController.getParticipant().setActiveCards(a);
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController) {
        if (checkPlay(arenaController))
            return false;

        if(arenaController.getEnemy().getHealth() >= arenaController.getEnemy().getMaxHealth())
            return false;

        if(!arenaController.getEnemy().getPlayStrategy().CheckOverTimeHeal(arenaController, card.getCost()))
            return false;

        activate(arenaController);
        return true;
    }

    @Override
    public void activateEndOfTurn(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();
        currentController.getParticipant().setHealth(currentController.getParticipant().getHealth() + card.getHPPerTurn());
        if(currentController.getParticipant().getHealth() > currentController.getParticipant().getMaxHealth()){
            currentController.getParticipant().setHealth(currentController.getParticipant().getMaxHealth());
        }
        card.decrementRoundsLeft();
    }
}
