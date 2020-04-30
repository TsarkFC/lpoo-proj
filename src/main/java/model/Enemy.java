package model;

import controller.strategies.NormalPlayStrategy;
import controller.strategies.PlayStrategy;

import java.util.List;

public class Enemy extends GameParticipant{
    PlayStrategy playStrategy;

    public Enemy(List<SpecialCard> play_deck, int health, int mana, int max_health, int max_mana, int max_points) {
        super(play_deck, health, mana, max_health, max_mana, max_points);
        this.playStrategy = new NormalPlayStrategy();
    }

    public PlayStrategy getPlayStrategy(){
        return playStrategy;
    }

    public void setPlayStrategy(PlayStrategy playStrategy){
        this.playStrategy = playStrategy;
    }
}
