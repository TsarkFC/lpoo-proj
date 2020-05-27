package com.g13.model.arena;

import com.g13.controller.arena.strategies.NormalPlayStrategy;
import com.g13.controller.arena.strategies.PlayStrategy;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    public void testEnemy(){
        BarSet barSet = Mockito.mock(BarSet.class);
        PlayStrategy strategy = new NormalPlayStrategy();
        Enemy enemy = new Enemy(new ArrayList<>(), barSet, strategy);
        assertEquals(strategy, enemy.getPlayStrategy());
    }
}