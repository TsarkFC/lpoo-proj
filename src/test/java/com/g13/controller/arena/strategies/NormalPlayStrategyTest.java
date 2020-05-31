package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalPlayStrategyTest {

    @Test
    public void playTurnTest(){
        ArenaController arn = initArena();
        arn.getPlayer().setHealth(10);
        arn.getEnemy().setPoints(3);

        int a = arn.getEnemy().getDrawDeck().get(0).getValue();
        boolean b = arn.getEnemy().getPlayStrategy().playTurn(arn);
        assertEquals(b, true);
    }


    private ArenaController initArena(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
        PlayStrategy strategy = new NormalPlayStrategy();
        List<SpecialCard> cards = new ArrayList<>();
        cards.add(new StaticModifier(0, 'a', "abc1", 1));
        cards.add(new AddHpPerTurn(0, 'a', "abc2", 5, 1));
        cards.add(new InstantDamage(0, 'a', "abc3", 3));
        cards.add(new OnWinDamage(0, 'a', "abc4", 4));
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
