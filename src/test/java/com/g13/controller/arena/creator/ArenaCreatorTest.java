package com.g13.controller.arena.creator;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.view.arena.ArenaViewer;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArenaCreatorTest {

    @Test
    public void testCreation(){
        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(10, 10);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController controller = new ArenaController(arenaViewer, arena, recognizer);
        ArenaCreator creator = new ArenaCreator();
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        GameParticipant part = Mockito.mock(GameParticipant.class);
        Mockito.when(part.getMana()).thenReturn(20);
        Mockito.when(part.getHealth()).thenReturn(20);
        Mockito.when(part.getPlayDeck()).thenReturn(player_special);

        creator.create(controller);

        assertEquals(part.getMana(), controller.getEnemy().getMana());
        assertEquals(part.getMana(), controller.getPlayer().getMana());
        assertEquals(part.getHealth(), controller.getEnemy().getHealth());
        assertEquals(part.getHealth(), controller.getPlayer().getHealth());
        assertEquals(part.getPlayDeck().size(), controller.getEnemy().getPlayDeck().size());
    }
}
