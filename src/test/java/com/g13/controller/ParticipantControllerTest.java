package com.g13.controller;

import com.g13.model.BarSet;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCardTypes.SpecialCard;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

    private ParticipantController createController(){
        List<SpecialCard> play_deck = new ArrayList<>();
        BarSet barSet = Mockito.mock(BarSet.class);

        GameParticipant player = new GameParticipant(play_deck, barSet);
        ParticipantController controller = new ParticipantController(player);
        controller.setDefaultDeck();

        return controller;
    }
}
