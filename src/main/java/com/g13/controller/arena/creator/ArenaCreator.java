package com.g13.controller.arena.creator;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.strategies.CarefulPlayStrategy;
import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.model.arena.Bar;
import com.g13.model.arena.BarSet;
import com.g13.model.arena.Enemy;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;

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

        return new Enemy(player_special, barSet, new CarefulPlayStrategy());
    }

    private List<SpecialCard> createSpecialDeck(){
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new FluxModifierAtoB(2, '+', "Instantly adds 1 to 3 to your points this round", 1, 3));
        player_special.add(new AddHpPerTurn(5, 'H', "Heals for 2 per round for 4 rounds", 2, 4));
        player_special.add(new StaticModifier(3, '+', "Instantly adds 1 to your points this round", 1));
        player_special.add(new OnWinDamage(10, 'F', "7 points of damage in case of stage victory", 7));
        player_special.add(new InstantDamage(4, 'F', "Instantly damages opponent with 4 points", 4));
        player_special.add(new AddHpPerTurn(4, 'H', "Heals for 5 per round for 1 round", 5, 1));
        player_special.add(new StaticModifier(2, '+', "Instantly adds 2 to your points this round", 2));
        return player_special;
    }

    public BarSet createBarSet(){
        Bar healthBar = new Bar(20, 20);
        Bar manaBar = new Bar(20, 20);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }

    public void create(ArenaController controller){
        controller.setPlayerController(createParticipant());
        controller.setEnemyController(createEnemy());
    }
}
