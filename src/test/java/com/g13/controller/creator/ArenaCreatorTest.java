package com.g13.controller.creator;

import com.g13.controller.ArenaController;
import com.g13.model.Arena;
import com.g13.model.GameParticipant;
import com.g13.model.SpecialCardTypes.SpecialCard;
import org.junit.Test;
import org.mockito.Mockito;
import com.g13.view.Gui;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArenaCreatorTest {

    @Test
    public void testCreation(){
        Gui gui = Mockito.mock(Gui.class);
        Arena arena = new Arena(10, 10);
        ArenaController controller = new ArenaController(gui, arena);
        ArenaCreator creator = new ArenaCreator();
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        GameParticipant part = Mockito.mock(GameParticipant.class);
        Mockito.when(part.getMana()).thenReturn(10);
        Mockito.when(part.getHealth()).thenReturn(10);
        Mockito.when(part.getPlay_deck()).thenReturn(player_special);

        creator.create(controller);

        assertEquals(part.getMana(), controller.getEnemy().getMana());
        assertEquals(part.getMana(), controller.getPlayer().getMana());
        assertEquals(part.getHealth(), controller.getEnemy().getHealth());
        assertEquals(part.getHealth(), controller.getPlayer().getHealth());
        assertEquals(part.getPlay_deck().size(), controller.getEnemy().getPlay_deck().size());
    }
}
