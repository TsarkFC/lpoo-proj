package com.g13.model;

import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.controller.strategies.PlayStrategy;

import java.util.List;

public class Enemy extends GameParticipant{
    PlayStrategy playStrategy;

    public Enemy(List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points, PlayStrategy playStrategy) {
        super(play_deck, health, mana, max_health, max_mana, max_points);
        this.playStrategy = playStrategy;
    }

    public PlayStrategy getPlayStrategy(){
        return playStrategy;
    }

    public void setPlayStrategy(PlayStrategy playStrategy){
        this.playStrategy = playStrategy;
    }
}
