package controller;

import model.Card;
import model.GameParticipant;

public class CardController {
    private Card card;

    public CardController(Card card){
        this.card = card;
    }

    public void effect(GameParticipant part){
        part.setPoints(part.getPoints() + card.getValue());
    }
}
