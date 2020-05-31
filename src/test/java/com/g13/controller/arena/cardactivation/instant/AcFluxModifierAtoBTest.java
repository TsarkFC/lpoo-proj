package com.g13.controller.arena.cardactivation.instant;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.FluxModifierAtoB;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class AcFluxModifierAtoBTest {

    @Test
    public void checkEnemyPlayTest(){
        ArenaController arn = initArena(0);
        FluxModifierAtoB sta = new FluxModifierAtoB(0, 'a', "abc", 3, 4);
        AcFluxModifierAtoB mod = new AcFluxModifierAtoB(sta);




        assertEquals(mod.checkEnemyPlay(arn, 0), true);
    }

    @Test
    public void activateTest(){
        ArenaController arn = initArena(0);
        FluxModifierAtoB sta = new FluxModifierAtoB(1, 'a', "abc", 3, 4);
        AcFluxModifierAtoB mod = new AcFluxModifierAtoB(sta);

        arn.getModel().setPlayersTurn(false);
        mod.activate(arn);
        assertEquals(arn.getEnemy().getMana(), 9);
        assertTrue(arn.getEnemy().getPoints() >= 3);
        assertTrue(arn.getEnemy().getPoints() <= 4);

    }

    private ArenaController initArena(int cardSelected){
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);

        List<SpecialCard> play_deck = new ArrayList<>();

        Enemy enemy = new Enemy(play_deck, initBar(), strategy);

        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(0,0);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController arenaController = new ArenaController(arenaViewer, arena, recognizer);

        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);
        Mockito.when(strategy.CheckFluxModifier(any(ArenaController.class), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(true);

        return arenaController;
    }

    private BarSet initBar() {
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet = new BarSet(healthBar, manaBar, pointBar);
        return barSet;
    }

}
