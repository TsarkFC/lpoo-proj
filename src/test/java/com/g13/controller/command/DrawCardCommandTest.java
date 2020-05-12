package com.g13.controller.command;

import com.g13.controller.ParticipantController;
import com.g13.controller.commands.DrawCardCommand;
import com.g13.model.Bar;
import com.g13.model.BarSet;
import com.g13.model.Card;
import com.g13.model.GameParticipant;
import org.junit.Test;
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
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), barSet);
        ParticipantController playerController = new ParticipantController(player);
        ParticipantController enemyController = new ParticipantController(enemy);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(5));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(10);

        DrawCardCommand command = new DrawCardCommand(playerController, enemyController);
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

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(2));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(12);

        DrawCardCommand command = new DrawCardCommand(playerController, enemyController);
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

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(3));
        player.setDrawDeck(drawDeck);

        playerController.setPoints(10);
        enemyController.setPoints(0);

        DrawCardCommand command = new DrawCardCommand(playerController, enemyController);
        command.execute();

        assertEquals(24, playerController.getDraw_deck().size());
        assertEquals(true, player.getTurnOver());
        assertEquals(true, enemy.getTurnOver());
        assertEquals(0, player.getPoints());
    }
}
