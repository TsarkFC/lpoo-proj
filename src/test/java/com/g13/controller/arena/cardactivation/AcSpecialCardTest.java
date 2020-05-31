package com.g13.controller.arena.cardactivation;

import com.g13.controller.arena.ArenaController;
import com.g13.controller.arena.cardactivation.instant.AcStaticModifier;
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

public class AcSpecialCardTest {

    @Property
    public void testRotateCardsSixCards(@ForAll @IntRange(min = 0, max = 4) int cardSelected){

        ArenaController arn = initArena(cardSelected);

        AcStaticModifier staMod = new AcStaticModifier((StaticModifier) arn.getEnemy().getPlayDeck().get(cardSelected));

        staMod.RotateCards(arn, cardSelected);

        assertEquals(arn.getEnemy().getPlayDeck().size(), 6);
        for(int i = 0; i < 4; i++) {
            if(i != cardSelected) {
                assertEquals(arn.getEnemy().getPlayDeck().get(i).getCardInfo(), String.valueOf(i));
            }
            else{
                assertEquals(arn.getEnemy().getPlayDeck().get(i).getCardInfo(), "4");
            }
        }
        assertEquals(arn.getEnemy().getPlayDeck().get(5).getCardInfo(), String.valueOf(cardSelected));
    }

    @Test
    public void testCheckPlay(){

        ArenaController arn = initArena(4);

        AcStaticModifier expensiveMod = new AcStaticModifier((StaticModifier) arn.getEnemy().getPlayDeck().get(3));
        AcStaticModifier cheapMod = new AcStaticModifier((StaticModifier) arn.getEnemy().getPlayDeck().get(0));

        boolean a = expensiveMod.checkPlay(arn);
        boolean b = cheapMod.checkPlay(arn);
        assertEquals(a, true);
        assertEquals(b, false);

    }


    private ArenaController initArena(int cardSelected){
        GameParticipant player = new GameParticipant(new ArrayList<>(), initBar());
        PlayStrategy strategy = Mockito.mock(PlayStrategy.class);

        List<SpecialCard> play_deck = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            play_deck.add(new StaticModifier(4 * i + 4, 'a',  String.valueOf(i), 1));
        }
        play_deck.get(cardSelected).setSelected(true);

        Enemy enemy = new Enemy(play_deck, initBar(), strategy);

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
