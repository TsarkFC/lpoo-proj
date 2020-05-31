package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.endofturn.AddHpPerTurn;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayStrategyTest {

    @Test
    public void checkStaticModifierTest(){
        ArenaController arn = initArena();

        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 1), false);
        arn.getEnemy().getPlayStrategy().setDraw_limit_reached(true);

        arn.getEnemy().setPoints(3);
        arn.getPlayer().setPoints(4);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 1), true);


        arn.getEnemy().setPoints(4);
        arn.getPlayer().setPoints(3);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 1), false);

        arn.getEnemy().setPoints(11);
        arn.getPlayer().setPoints(12);
        assertEquals(arn.getEnemy().getPlayStrategy().CheckStaticModifier(arn, 0, 2), false);

    }

    @Test
    public void checkFluxModifierTest(){
        ArenaController arn = initArena();


        arn.getEnemy().setPoints(3);
        arn.getPlayer().setPoints(4);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckFluxModifier(arn, 0, 1, 2), true);


        arn.getEnemy().setPoints(4);
        arn.getPlayer().setPoints(3);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckFluxModifier(arn, 0, 1, 2), true);

        arn.getEnemy().setPoints(11);
        arn.getPlayer().setPoints(12);
        assertEquals(arn.getEnemy().getPlayStrategy().CheckFluxModifier(arn, 0, 2, 5), false);
    }

    /*
    public boolean CheckFluxModifier(ArenaController arenaController, int cost, int minModNum, int maxModNum){
        int result =  (int) Math.ceil(flux_percentage_accept * (maxModNum - minModNum + 1) + minModNum);

        return result <= arenaController.getEnemy().getMaxPoints() - arenaController.getEnemy().getPoints()
                && HasEnoughManaToWantToPlay(arenaController, cost);
    }*/

    @Test
    public void checkOverTimeHealTest(){
        ArenaController arn = initArena();

        assertEquals(arn.getEnemy().getPlayStrategy().CheckOverTimeHeal(arn, 0), false);
        arn.getEnemy().setHealth(1);

        assertEquals(arn.getEnemy().getPlayStrategy().CheckOverTimeHeal(arn, 0), true);

        List<SpecialCard> activeCards = new ArrayList<SpecialCard>();

        activeCards.add(new AddHpPerTurn(0, 'a', "abc", 1, 2));
        arn.getEnemy().setActiveCards(activeCards);

    }

    @Test
    public void getDraw_limit_reachedTest(){
        ArenaController arn = initArena();
        assertEquals(arn.getEnemy().getPlayStrategy().getDraw_limit_reached(), false);
        arn.getEnemy().getPlayStrategy().setDraw_limit_reached(true);
        assertEquals(arn.getEnemy().getPlayStrategy().getDraw_limit_reached(), true);
    }

    private ArenaController initArena(){
        List<SpecialCard> play_deck = new ArrayList<>();

        //play_deck.get(0).setSelected(true);

        GameParticipant player = new GameParticipant(play_deck, initBar());
        PlayStrategy strategy = new NormalPlayStrategy();
        Enemy enemy = new Enemy(new ArrayList<>(), initBar(), strategy);

        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(0,0);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController arenaController = new ArenaController(arenaViewer, arena, recognizer);

        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);
        return arenaController;
    }







    private BarSet initBar(){
        Bar healthBar = new Bar(10, 20);
        Bar manaBar = new Bar(10, 20);
        Bar pointBar = new Bar(0, 12);
        BarSet barSet =  new BarSet(healthBar, manaBar, pointBar);
        return barSet;
    }

    private BarSet initBarSet(){
        Bar healthBar = new Bar(2, 12);
        Bar manaBar = new Bar(5, 12);
        Bar pointBar = new Bar(0, 12);
        return new BarSet(healthBar, manaBar, pointBar);
    }
}
