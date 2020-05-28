package com.g13.controller.arena.cardactivation.endofturn;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;

import java.util.List;

public class AcOnWinDamage extends AcSpecialCard implements EndOfTurn {
    OnWinDamage card;

    public AcOnWinDamage(OnWinDamage card) {
        super(card);
        this.card = card;
    }

    @Override
    public void activate(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();
        //Subtract mana on play
        currentController.getParticipant().setMana(currentController.getParticipant().getMana() - card.getCost());
        List<SpecialCard> a = currentController.getParticipant().getActiveCards();

        OnWinDamage c = card;

        a.add(c);
        currentController.getParticipant().setActiveCards(a);
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController) {
        return false;
    }

    @Override
    public void activateEndOfTurn(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();
        ParticipantController opponentController = arenaController.getOpponent();

        if (currentController == arenaController.getWinner())
            opponentController.subtractHealth(card.getDamage());
    }
}
