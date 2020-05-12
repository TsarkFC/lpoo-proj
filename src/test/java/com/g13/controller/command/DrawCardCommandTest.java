package com.g13.controller.command;

import com.g13.controller.ArenaController;
import com.g13.controller.ParticipantController;
import com.g13.controller.commands.DrawCardCommand;
import com.g13.controller.strategies.PlayStrategy;
import com.g13.model.*;
import com.g13.view.Gui;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DrawCardCommandTest {
    @Test
    public void testExecuteOverFlow(){
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet =  new BarSet(healthBar, manaBar, pointBar);

        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);
        Enemy enemy = new Enemy(new ArrayList<>(), barSet, strategy);

        Gui gui = Mockito.mock(Gui.class);
        Arena arena = Mockito.mock(Arena.class);
        ArenaController arenaController = new ArenaController(gui, arena);
        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);

        ParticipantController playerController = arenaController.getPlayerController();
        ParticipantController enemyController = arenaController.getEnemyController();

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(5));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(10);

        DrawCardCommand command = new DrawCardCommand(arenaController, playerController, enemyController);
        command.execute();

        assertEquals(24, playerController.getDraw_deck().size());
        assertEquals(true, player.getTurnOver());
        assertEquals(true, enemy.getTurnOver());
        assertEquals(6, player.getPoints());
    }

    @Test
    public void testExecuteBothMax(){
        BarSet barSet = Mockito.mock(BarSet.class);
        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), barSet);
        ParticipantController playerController = new ParticipantController(player);
        ParticipantController enemyController = new ParticipantController(enemy);
        ArenaController arenaController = Mockito.mock(ArenaController.class);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(2));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(12);

        DrawCardCommand command = new DrawCardCommand(arenaController, playerController, enemyController);
        command.execute();

        assertEquals(24, playerController.getDraw_deck().size());
        assertEquals(true, player.getTurnOver());
        assertEquals(true, enemy.getTurnOver());
    }

    @Test
    public void testExecuteNegativeA(){
        BarSet barSet = Mockito.mock(BarSet.class);
        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), barSet);
        ParticipantController playerController = new ParticipantController(player);
        ParticipantController enemyController = new ParticipantController(enemy);
        ArenaController arenaController = Mockito.mock(ArenaController.class);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(3));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(0);

        DrawCardCommand command = new DrawCardCommand(arenaController, playerController, enemyController);
        command.execute();

        assertEquals(24, playerController.getDraw_deck().size());
        assertEquals(true, player.getTurnOver());
        assertEquals(true, enemy.getTurnOver());
        assertEquals(0, player.getPoints());
    }
}
