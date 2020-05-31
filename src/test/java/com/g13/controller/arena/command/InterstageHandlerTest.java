package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.ParticipantController;
import com.g13.controller.arena.commands.InterstageHandler;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.view.arena.ArenaViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.mockito.Mockito;

import java.util.ArrayList;

import static java.lang.Integer.max;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterstageHandlerTest {

    @Property
    public void CombatTest(@ForAll @IntRange(min = 0, max = 12) int playerPoints, @ForAll @IntRange(min = 0, max = 12) int enemyPoints){

        ArenaController arenaController = initArena();
        ParticipantController playerController = arenaController.getPlayerController();
        ParticipantController enemyController = arenaController.getEnemyController();


        playerController.setPoints(playerPoints);
        enemyController.setPoints(enemyPoints);

        InterstageHandler cmd = new InterstageHandler(arenaController);
        cmd.execute();

        assertEquals(arenaController.getPlayerController().getPoints(), max(playerPoints - enemyPoints, 0));
        assertEquals(arenaController.getEnemyController().getPoints(), max(enemyPoints - playerPoints, 0));
    }

    @Property
    public void CheckSubtractionPlayer(@ForAll @IntRange(min = 0,max = 10) int enemyPoints){
        ArenaController arn = initArena();

        arn.getPlayerController().setHealth(11);
        arn.getPlayerController().setPoints(0);

        arn.getPlayerController().setPoints(0);
        arn.getEnemyController().setPoints(enemyPoints);
        arn.advanceCmdStage();

        InterstageHandler cmd = new InterstageHandler(arn);
        cmd.execute();

        assertEquals(arn.getPlayerController().getHealth(), 11 - enemyPoints);
    }

    private ArenaController initArena() {
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);
        Enemy enemy = new Enemy(new ArrayList<>(), initBar(), strategy);

        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(0, 0);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController arenaController = new ArenaController(arenaViewer, arena, recognizer);

        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);
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