package com.g13.controller;

import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.controller.strategies.PlayStrategy;
import com.g13.model.Arena;
import com.g13.model.BarSet;
import com.g13.model.Enemy;
import com.g13.model.GameParticipant;
import com.g13.view.Gui;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ArenaControllerTest {
    @Test
    public void testsetPlayerController(){
        BarSet barSet = Mockito.mock(BarSet.class);
        ArenaController controller = initArena();
        GameParticipant player = new GameParticipant(new ArrayList<>(), barSet);

        controller.setPlayerController(player);

        assertEquals(player, controller.getPlayerController().getParticipant());
        assertEquals(24, controller.getPlayerController().getParticipant().getDrawDeck().size());
    }

    @Test
    public void testsetEnemyontroller(){
        BarSet barSet = Mockito.mock(BarSet.class);
        ArenaController controller = initArena();
        PlayStrategy strategy = Mockito.mock(NormalPlayStrategy.class);
        Enemy enemy = new Enemy(new ArrayList<>(), barSet, strategy);

        controller.setEnemyController(enemy);

        assertEquals(enemy, controller.getEnemyController().getParticipant());
        assertEquals(24, controller.getEnemyController().getParticipant().getDrawDeck().size());
    }

    private ArenaController initArena(){
        Gui gui = Mockito.mock(Gui.class);
        Arena arena = Mockito.mock(Arena.class);
        return new ArenaController(gui, arena);
    }
}
