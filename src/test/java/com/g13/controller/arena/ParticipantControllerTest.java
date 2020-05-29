package com.g13.controller.arena;

import com.g13.model.arena.Bar;
import com.g13.model.arena.BarSet;
import com.g13.model.arena.Card;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.InstantDamage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipantControllerTest {
    @Test
    public void testsetDefaultDrawDeck(){
        ParticipantController controller = createController();

        assertEquals(controller.getParticipant().getDrawDeck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++)
            a += controller.getDrawDeck().get(i).getValue();

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }

    @Test
    public void testresetDrawDeck(){

        ParticipantController controller = createController();

        controller.getParticipant().setDrawDeck(new ArrayList<>());
        assertEquals(controller.getParticipant().getDrawDeck().size(), 0);

        controller.resetDrawDeck();
        assertEquals(controller.getParticipant().getDrawDeck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++)
            a += controller.getDrawDeck().get(i).getValue();

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }

    @Test
    public void testremoveDeckTop(){
        ParticipantController controller = createController();
        for (int i = 0; i<7; i++)
            controller.removeDeckTop();
        assertEquals(controller.getParticipant().getDrawDeck().size(), 17);
    }

    @Test
    public void resetPlayerTest(){
        GameParticipant player = createPlayer();
        player.setHealth(10);
        player.setMana(10);

        ParticipantController controller = new ParticipantController(player);
        controller.resetPlayer();

        assertEquals(player.getMaxHealth(), player.getHealth());
        assertEquals(player.getMaxMana(), player.getMana());
        assertEquals(0, player.getPoints());
        assertEquals(0, player.getActiveCards().size());
    }

    @Test
    public void resetOnWinTest(){
        GameParticipant player = createPlayer();
        player.setHealth(10);
        player.setMana(10);

        ParticipantController controller = new ParticipantController(player);
        controller.resetOnWin();

        assertEquals(10, player.getHealth());
        assertEquals(player.getMaxMana(), player.getMana());
        assertEquals(0, player.getPoints());
        assertEquals(0, player.getActiveCards().size());
    }

    @Test
    public void testSelectedCard(){
        GameParticipant player = createPlayer();
        List<SpecialCard> deck = player.getPlayDeck();
        InstantDamage card = new InstantDamage(3, '*', " ", 5);
        card.setSelected(true);
        deck.add(card);

        ParticipantController controller = new ParticipantController(player);
        assertEquals(1, controller.getSelected());

        controller.setCardSelected(0);
        assertEquals(0, controller.getSelected());
        controller.setCardSelected(0);
        assertEquals(-1, controller.getSelected());
    }

    @Test
    public void testSubtractors(){
        ParticipantController controller = new ParticipantController(createPlayer());
        controller.subtractHealth(5);
        controller.subtractMana(10);
        controller.subtractPoints(10);
        assertEquals(0, controller.getParticipant().getMana());
        assertEquals(0, controller.getParticipant().getHealth());
        assertEquals(-10, controller.getParticipant().getPoints());
    }

    private ParticipantController createController(){
        List<SpecialCard> play_deck = new ArrayList<>();
        BarSet barSet = Mockito.mock(BarSet.class);

        GameParticipant player = new GameParticipant(play_deck, barSet);
        ParticipantController controller = new ParticipantController(player);
        controller.setDefaultDeck();

        return controller;
    }

    private GameParticipant createPlayer(){
        List<SpecialCard> deck = new ArrayList<>();
        deck.add(new InstantDamage(3, '*', " ", 3));

        GameParticipant player = new GameParticipant(deck, initBarSet());
        player.setDefaultDrawDeck(new ArrayList<>());
        player.setActiveCards(deck);
        return player;
    }

    private BarSet initBarSet(){
        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }
}
