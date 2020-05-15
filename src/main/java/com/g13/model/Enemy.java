package com.g13.model;

import com.g13.controller.strategies.PlayStrategy;
import com.g13.model.specialcards.SpecialCard;

import java.util.List;

public class Enemy extends GameParticipant{
    PlayStrategy playStrategy;

    public Enemy(List<SpecialCard> play_deck, BarSet barSet, PlayStrategy playStrategy) {
        super(play_deck, barSet);
        this.playStrategy = playStrategy;
    }

    public PlayStrategy getPlayStrategy(){
        return playStrategy;
    }
}
