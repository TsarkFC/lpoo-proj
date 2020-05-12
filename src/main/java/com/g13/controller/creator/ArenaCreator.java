package com.g13.controller.creator;

import com.g13.controller.ArenaController;
import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.model.*;
import com.g13.model.SpecialCardTypes.Instant.FluxModifierAtoB;
import com.g13.model.SpecialCardTypes.SpecialCard;
import com.g13.model.SpecialCardTypes.Instant.StaticModifier;

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
        player_special.add(new FluxModifierAtoB(2, '*', "Instantly adds 1 to 3 to your points this round", 1, 3));
        player_special.add(new StaticModifier(7, '+', "Instantly adds 3 to your points this round", 3));
        player_special.add(new StaticModifier(4, '-', "Instantly adds 1 to your points this round", 1));
        player_special.add(new StaticModifier(5, '/', "Instantly adds 2 to your points this round", 2));
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
