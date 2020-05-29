package com.g13.controller.arena.cardactivation.endofturn;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.cardactivation.AcSpecialCard;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.DamageGamble;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;

import java.util.List;

import static java.lang.Integer.min;

public class AcDamageGamble extends AcSpecialCard implements EndOfTurn{

    DamageGamble card;

    public AcDamageGamble(DamageGamble card) {
        super(card);
        this.card = card;
    }

    @Override
    public void activate(ArenaController arenaController) {
        ParticipantController currentController = arenaController.getCurrent();

        currentController.subtractMana(card.getCost());

        List<SpecialCard> deck = currentController.getParticipant().getActiveCards();

        DamageGamble c = card;



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

        currentController.setMana(min(currentController.getMana() + opponentController.getPoints() * card.getmanaPerDamageTaken(), currentController.getMaxMana()));
    }
}
