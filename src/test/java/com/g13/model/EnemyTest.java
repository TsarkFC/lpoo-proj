package com.g13.model;

import com.g13.controller.strategies.NormalPlayStrategy;
import com.g13.controller.strategies.PlayStrategy;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EnemyTest {
    @Test
    public void testEnemy(){
        PlayStrategy strategy = new NormalPlayStrategy();
        Enemy enemy = new Enemy(new ArrayList<>(), 0, 0, 0, 0, 0, strategy);
        assertEquals(strategy, enemy.getPlayStrategy());
    }
}
