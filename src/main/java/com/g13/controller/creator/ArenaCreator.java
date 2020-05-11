package com.g13.controller.creator;

import com.g13.controller.ArenaController;
import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.model.*;

import java.util.ArrayList;
import java.util.List;

public class ArenaCreator {
    private GameParticipant createParticipant(){
        List<SpecialCard> player_special = createSpecialDeck();
        BarSet barSet = createBarSet();

        return new GameParticipant(player_special, barSet);
    }

    private Enemy createEnemy(){
        List<SpecialCard> player_special = createSpecialDeck();
        BarSet barSet = createBarSet();

        return new Enemy(player_special, barSet, new NormalPlayStrategy());
    }

    private List<SpecialCard> createSpecialDeck(){ //TODO: make different list for different enemies/for th eplayer
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        return player_special;
    }

    public BarSet createBarSet(){
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }

    public void create(ArenaController controller){
        controller.setPlayerController(createParticipant());
        controller.setEnemyController(createEnemy());
    }
}
