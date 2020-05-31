package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.commands.DrawCardCommand;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarefulPlayStrategyTest {

    @Test
    public void playTurnTest(){
        ArenaController arn = initArena();
        arn.getEnemy().setPoints(0);

        int a = arn.getEnemy().getDrawDeck().get(0).getValue();
        assertEquals(arn.getEnemy().getPlayStrategy().playTurn(arn), true);
    }

    private ArenaController initArena(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
        PlayStrategy strategy = new CarefulPlayStrategy();
        List<SpecialCard> cards = new ArrayList<>();
        cards.add(new StaticModifier(0, 'a', "abc", 1));
        Enemy enemy = new Enemy(cards, initBar(), strategy);
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
}
