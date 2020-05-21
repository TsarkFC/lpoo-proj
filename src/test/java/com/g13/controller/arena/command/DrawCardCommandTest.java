package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.commands.DrawCardCommand;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.view.arena.ArenaViewer;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DrawCardCommandTest {
    @Test
    public void testExecuteOverFlow(){
        ArenaController arenaController = initArena();
        ParticipantController playerController = arenaController.getPlayerController();
        ParticipantController enemyController = arenaController.getEnemyController();

        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(5));
        playerController.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(10);

        DrawCardCommand command = new DrawCardCommand(arenaController);
        command.execute();

        assertEquals(24, playerController.getDrawDeck().size());
        assertEquals(true, playerController.getParticipant().getTurnOver());
        assertEquals(true, enemyController.getParticipant().getTurnOver());
        assertEquals(6, playerController.getPoints());
    }

    @Test
    public void testExecuteBothMax(){
        ArenaController arenaController = initArena();
        ParticipantController playerController = arenaController.getPlayerController();
        ParticipantController enemyController = arenaController.getEnemyController();

        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(2));
        drawDeck.add(new Card(4));
        playerController.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(12);

        DrawCardCommand command = new DrawCardCommand(arenaController);
        command.execute();

        assertEquals(1, playerController.getDrawDeck().size());
        assertEquals(true, playerController.getParticipant().getTurnOver());
        assertEquals(true, enemyController.getParticipant().getTurnOver());
    }

    @Test
    public void testExecuteNegative(){
        ArenaController arenaController = initArena();
        ParticipantController playerController = arenaController.getPlayerController();
        ParticipantController enemyController = arenaController.getEnemyController();

        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(3));
        playerController.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(0);

        DrawCardCommand command = new DrawCardCommand(arenaController);
        command.execute();

        assertEquals(true, playerController.getParticipant().getTurnOver());
        assertEquals(true, enemyController.getParticipant().getTurnOver());
        assertEquals(0, playerController.getPoints());
        assertEquals(24, playerController.getDrawDeck().size());
    }

    private ArenaController initArena(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
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
}
