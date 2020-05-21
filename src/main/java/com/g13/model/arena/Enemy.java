package com.g13.model.arena;

import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.model.arena.specialcards.SpecialCard;

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
