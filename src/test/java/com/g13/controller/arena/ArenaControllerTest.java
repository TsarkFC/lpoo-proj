package com.g13.controller.arena;

import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.State;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.model.arena.BarSet;
import com.g13.model.arena.Enemy;
import com.g13.model.arena.GameParticipant;
import com.g13.view.arena.ArenaViewer;
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
        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = Mockito.mock(Arena.class);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        return new ArenaController(arenaViewer, arena, recognizer);
    }
}
