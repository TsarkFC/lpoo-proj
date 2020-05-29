package com.g13.controller.arena.cardactivation.endofturn;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.SpecialCard;
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

        currentController.subtractMana(card.getCost());
        List<SpecialCard> deck = currentController.getParticipant().getActiveCards();

        OnWinDamage c = card;



        deck.add(c);
        currentController.getParticipant().setActiveCards(deck);
    }

    @Override
    public boolean checkEnemyPlay(ArenaController arenaController, int cardPos) {
        if (checkPlay(arenaController))
            return false;
        RotateCards(arenaController, cardPos);
        activate(arenaController);
        return true;
    }

    @Override
    public void activateEndOfTurn(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();
        ParticipantController opponentController = arenaController.getOpponent();

        if (currentController.getPoints() != 0) {
            opponentController.subtractHealth(card.getDamage());
        }
    }
}
