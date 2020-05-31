package com.g13.controller.arena.command;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.commands.PlaySpecialCardCommand;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.*;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.model.arena.specialcards.instant.StaticModifier;
import com.g13.view.arena.ArenaViewer;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaySpecialCardCommandTest {

    @Property
    public void TestStaticModifier(@ForAll @IntRange(min = 1,max = 10) int modNum){
        ArenaController arn = initArenaWithModifier(modNum);
        PlaySpecialCardCommand card = new PlaySpecialCardCommand(arn);
        card.execute();
        assertEquals(arn.getPlayerController().getPoints(), modNum);
    }

    @Test
    public void testSixCards(){
        ArenaController arn = initArena(3);
        PlaySpecialCardCommand cmd = new PlaySpecialCardCommand(arn);
        cmd.execute();

        for(int i = 0; i < 4; i++) {
            if(i != 3) {
                assertEquals(arn.getPlayer().getPlayDeck().get(i).getCardInfo(), String.valueOf(i));
            }
            else{
                assertEquals(arn.getPlayer().getPlayDeck().get(i).getCardInfo(), "4");
            }
        }
        assertEquals(arn.getPlayer().getPlayDeck().get(5).getCardInfo(), String.valueOf(3));

    }


    private ArenaController initArena(int cardSelected){

        List<SpecialCard> play_deck = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            play_deck.add(new StaticModifier(0, 'a',  String.valueOf(i), 1));
        }
        play_deck.get(cardSelected).setSelected(true);


        GameParticipant player = new GameParticipant(play_deck, initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);

        Enemy enemy = new Enemy(new ArrayList<>(), initBar(), strategy);

        ArenaViewer arenaViewer = Mockito.mock(ArenaViewer.class);
        Arena arena = new Arena(0,0);
        StateRecognizer recognizer = Mockito.mock(StateRecognizer.class);
        ArenaController arenaController = new ArenaController(arenaViewer, arena, recognizer);

        arenaController.setEnemyController(enemy);
        arenaController.setPlayerController(player);
        return arenaController;
    }

    private ArenaController initArenaWithModifier(int modNum){
        List<SpecialCard> play_deck = new ArrayList<>();
        play_deck.add(new StaticModifier(0, 'a', "", modNum));

        play_deck.get(0).setSelected(true);

        GameParticipant player = new GameParticipant(play_deck, initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);
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
}
