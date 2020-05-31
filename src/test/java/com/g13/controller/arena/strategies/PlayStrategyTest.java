package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayStrategyTest {
/*
    @Test
    public void CheckStaticModifierTest(){
        ArenaController arn = initArena();

        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 1), false);
        arn.getEnemy().getPlayStrategy().setDraw_limit_reached(true);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 1), true);

    }

    private ArenaController initArena(){
        List<SpecialCard> play_deck = new ArrayList<>();

        play_deck.get(0).setSelected(true);

        GameParticipant player = new GameParticipant(play_deck, initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);
        Enemy enemy = new Enemy(new ArrayList<>(), initBar(), strategy);

        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(0,0);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController arenaController = new ArenaController(arenaViewer, arena, recognizer);

        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);
        return arenaController;
    }

    private BarSet initBar(){
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet =  new BarSet(healthBar, manaBar, pointBar);
        return barSet;
    }

    private BarSet initBarSet(){
        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }*/
}
