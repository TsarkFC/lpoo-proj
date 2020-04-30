package controller;

import model.Card;
import model.GameParticipant;
import model.SpecialCard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParticipantControllerTest {
    @Test
    public void testEmptyDeckController(){
        List<SpecialCard> play_deck = new ArrayList<>();

        GameParticipant player = new GameParticipant(play_deck, 2, 5, 12, 12, 12);
        GameParticipantController controller = new GameParticipantController(player);
        controller.setDefaultDeck();

        assertEquals(player.getDraw_deck().size(), 24);

        int a = 0;
        for(int i = 0; i < 24; i++){
            a += player.getDraw_deck().get(i).getValue();
        }

        assertEquals(a, 4 + 8 + 12 + 16 + 20 + 24);
    }
}
