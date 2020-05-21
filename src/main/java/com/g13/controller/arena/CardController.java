package com.g13.controller.arena;

import com.g13.model.arena.Card;
import com.g13.model.arena.GameParticipant;

public class CardController {
    private Card card;

    public CardController(Card card){
        this.card = card;
    }

    public void effect(GameParticipant part){
        part.setPoints(part.getPoints() + card.getValue());
    }
}
