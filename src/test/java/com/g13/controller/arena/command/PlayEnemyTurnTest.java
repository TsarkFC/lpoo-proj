package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.commands.PlayEnemyTurn;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.model.arena.Arena;
import com.g13.model.arena.Enemy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayEnemyTurnTest {
    @Test
    public void playEnemyTurnTest(){
        Arena arena = Mockito.mock(Arena.class);
        ParticipantController enemyController = Mockito.mock(ParticipantController.class);
        ArenaController controller = Mockito.mock(ArenaController.class);
        Enemy enemy = Mockito.mock(Enemy.class);
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);
        Mockito.when(controller.getEnemy()).thenReturn(enemy);
        Mockito.when(enemy.getPlayStrategy()).thenReturn(strategy);
        Mockito.when(controller.getModel()).thenReturn(arena);
        Mockito.doNothing().when(arena).setPlayersTurn(true);
        Mockito.doNothing().when(arena).setPlayersTurn(false);
        Mockito.when(controller.getEnemyController()).thenReturn(enemyController);
        Mockito.doNothing().when(enemyController).setTurnOver(true);

        PlayEnemyTurn cmd = new PlayEnemyTurn(controller);
        cmd.execute();

        Mockito.verify(strategy, Mockito.times(1)).playTurn(controller);
    }
}
