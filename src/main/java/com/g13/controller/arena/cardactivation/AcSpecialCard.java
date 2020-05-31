package com.g13.controller.arena.cardactivation;

import com.g13.controller.arena.ArenaController;
import com.g13.model.arena.specialcards.SpecialCard;

import java.util.Collections;
import java.util.List;

public abstract class AcSpecialCard {

    private SpecialCard card;

    public AcSpecialCard(SpecialCard card){ this.card = card; }

    public boolean checkPlay(ArenaController arenaController) {
        return arenaController.getEnemy().getMana() < card.getCost();
    }

    public abstract void activate(ArenaController arenaController);
    public abstract boolean checkEnemyPlay(ArenaController arenaController, int cardPos);
    protected void RotateCards(ArenaController arenaController, int cardPos){
        List<SpecialCard> cards = arenaController.getEnemy().getPlayDeck();
        if(cards.size() > 4) {
            Collections.swap(cards, cardPos, 4);
            SpecialCard card = cards.get(4);
            cards.remove(4);
            cards.add(card);
        }
    }
}
