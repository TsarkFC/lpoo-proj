package com.g13.model;

import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.controller.strategies.PlayStrategy;
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
