package com.g13.controller;

import com.g13.controller.GameParticipantController;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParticipantControllerTest {
    @Test
    public void testsetDefaultDrawDeck(){
        GameParticipantController controller = createController();

        assertEquals(controller.getParticipant().getDraw_deck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++){
            a += controller.getParticipant().getDraw_deck().get(i).getValue(); // fix
        }

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }

    @Test
    public void testresetDrawDeck(){

        GameParticipantController controller = createController();

        controller.getParticipant().setDraw_deck(new ArrayList<>());
        assertEquals(controller.getParticipant().getDraw_deck().size(), 0);

        controller.resetDrawDeck();
        assertEquals(controller.getParticipant().getDraw_deck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++){
            a += controller.getParticipant().getDraw_deck().get(i).getValue();
        }

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }

    @Test
    public void testremoveDeckTop(){
        GameParticipantController controller = createController();

        for (int i = 0; i<7; i++)
            controller.removeDeckTop();
        assertEquals(controller.getParticipant().getDraw_deck().size(), 17);
    }

    private GameParticipantController createController(){
        List<SpecialCard> play_deck = new ArrayList<>();

        GameParticipant player = new GameParticipant(play_deck, 2, 5, 12, 12, 12);
        GameParticipantController controller = new GameParticipantController(player);
        controller.setDefaultDeck();

        return controller;
    }
}
