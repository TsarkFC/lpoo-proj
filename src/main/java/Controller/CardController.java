package Controller;

import Model.Card;
import Model.GameParticipant;

public class CardController {
    private Card card;

    public CardController(Card card){
        this.card = card;
    }

    public void effect(GameParticipant part){
        part.setPoints(part.getPoints() + card.getValue());
    }
}
