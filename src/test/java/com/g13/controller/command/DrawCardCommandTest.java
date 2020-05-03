package com.g13.controller.command;

import com.g13.controller.GameParticipantController;
import com.g13.controller.commands.DrawCardCommand;
import com.g13.model.Card;
import com.g13.model.GameParticipant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DrawCardCommandTest {
    @Test
    public void testExecuteOverFlow(){
        GameParticipant player = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipantController playerController = new GameParticipantController(player);
        GameParticipantController enemyController = new GameParticipantController(enemy);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(5));
        player.setDraw_deck(drawDeck);

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
        GameParticipant player = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipantController playerController = new GameParticipantController(player);
        GameParticipantController enemyController = new GameParticipantController(enemy);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(2));
        player.setDraw_deck(drawDeck);

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
        GameParticipant player = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipant enemy = new GameParticipant(new ArrayList<>(), 10, 10, 20, 20, 12);
        GameParticipantController playerController = new GameParticipantController(player);
        GameParticipantController enemyController = new GameParticipantController(enemy);

        playerController.setDefaultDeck();
        List<Card> drawDeck = new ArrayList<>();
        drawDeck.add(new Card(3));
        player.setDraw_deck(drawDeck);

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
