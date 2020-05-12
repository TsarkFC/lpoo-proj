package com.g13.controller;

import com.g13.model.Card;
import com.g13.model.GameParticipant;

public class CardController {
    private Card card;

    public CardController(Card card){
        this.card = card;
    }

    public void effect(GameParticipant part){
        part.setPoints(part.getPoints() + card.getValue());
    }
}
