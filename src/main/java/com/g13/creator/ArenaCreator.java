package com.g13.creator;

import com.g13.controller.ArenaController;
import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.model.Enemy;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCard;

import java.util.ArrayList;
import java.util.List;

public class ArenaCreator {
    private GameParticipant createParticipant(){
        List<SpecialCard> player_special = createSpecialDeck();

        return new GameParticipant(player_special, 10, 10, 20, 20, 12);
    }

    private Enemy createEnemy(){
        List<SpecialCard> player_special = createSpecialDeck();

        return new Enemy(player_special, 10, 10, 20, 20, 12, new NormalPlayStrategy());
    }

    private List<SpecialCard> createSpecialDeck(){ //TODO: make different list for different enemies/for th eplayer
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        return player_special;
    }

    public void create(ArenaController controller){
        controller.setPlayerController(createParticipant());
        controller.setEnemyController(createEnemy());
    }
}