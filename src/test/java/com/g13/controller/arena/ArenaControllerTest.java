package com.g13.controller.arena;

import com.g13.controller.arena.strategies.AggressivePlayStrategy;
import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.LevelState;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.endofturn.OnWinDamage;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class ArenaControllerTest {
    @Test
    public void testsetPlayerController(){
        BarSet barSet = Mockito.mock(BarSet.class);
        ArenaController controller = initArena();
        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);

        controller.setPlayerController(player);

        assertEquals(10, controller.getModel().getWidth());
        assertEquals(player, controller.getPlayerController().getParticipant());
        assertEquals(24, controller.getPlayerController().getParticipant().getDrawDeck().size());
    }

    @Test
    public void testsetEnemyontroller(){
        BarSet barSet = Mockito.mock(BarSet.class);
        ArenaController controller = initArena();
        PlayStrategy strategy = Mockito.mock(NormalPlayStrategy.class);
        Enemy enemy = new Enemy(new ArrayList<>(), barSet, strategy);

        controller.setEnemyController(enemy);

        assertEquals(enemy, controller.getEnemyController().getParticipant());
        assertEquals(24, controller.getEnemyController().getParticipant().getDrawDeck().size());
    }

    @Test
    public void loserWinnerTest(){
        PlayStrategy strategy = Mockito.mock(NormalPlayStrategy.class);
        ArenaController controller = initArena();
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBarSet());
        player.setPoints(0);
        Enemy enemy = new Enemy(new ArrayList<>(), initBarSet(), strategy);
        enemy.setPoints(4);
        controller.setPlayerController(player);
        controller.setEnemyController(enemy);
        ParticipantController winner = controller.getWinner();
        ParticipantController loser = controller.getLoser();

        assertEquals(0, loser.getPoints());
        assertEquals(true, winner.getPoints() != 0);
    }

    @Test
    public void testStrategySetting(){
        ArenaController controller = initArena();
        PlayStrategy playStrategy = new NormalPlayStrategy();
        Enemy enemy = new Enemy(new ArrayList<>(), initBarSet(), playStrategy);
        controller.setEnemyController(enemy);
        controller.setEnemyStrategy(new AggressivePlayStrategy());
        assertTrue(enemy.getPlayStrategy() instanceof AggressivePlayStrategy);
    }

    @Test
    public void processPlayerCards(){
        ArenaController controller = initArena();
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBarSet());

        OnWinDamage onWinDamage1 = new OnWinDamage(2, '+', " ", 10);
        AddHpPerTurn addHpPerTurn1 = new AddHpPerTurn(2, '*', " ", 2, 1);
        OnWinDamage onWinDamage2 = new OnWinDamage(2, '+', " ", 10);
        AddHpPerTurn addHpPerTurn2 = new AddHpPerTurn(2, '*', " ", 2, 4);

        List<SpecialCard> deck = new ArrayList<>();
        deck.add(onWinDamage1);
        deck.add(addHpPerTurn1);
        deck.add(onWinDamage2);
        deck.add(addHpPerTurn2);
        player.setActiveCards(deck);

        controller.setPlayerController(player);
        controller.processPlayerCards(controller.getPlayerController());
        assertEquals(1, player.getActiveCards().size());
    }

    private ArenaController initArena(){
        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(10, 10);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        return new ArenaController(arenaViewer, arena, recognizer);
    }

    private BarSet initBarSet(){
        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }
}
