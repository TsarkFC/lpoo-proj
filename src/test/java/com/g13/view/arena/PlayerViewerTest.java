package com.g13.view.arena;

import com.g13.model.arena.BarSet;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.SpecialCard;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewerTest {

    BarViewer barViewer = Mockito.mock(BarViewer.class);
    CardViewer cardViewer = Mockito.mock(CardViewer.class);
    TextGraphics graphics = Mockito.mock(TextGraphics.class);

    @Test
    public void testDrawPlayer(){
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        BarSet barSet = Mockito.mock(BarSet.class);

        GameParticipant player = new GameParticipant(player_special, barSet);
        player.setDrawDeck(new ArrayList<>());

        GameParticipantViewer viewer = new GameParticipantViewer(barViewer, cardViewer, graphics);
        viewer.drawPlayer(player);

        Mockito.verify(cardViewer, Mockito.times(1))
                .drawCard(1, 19, 0);
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(12, player.getPlayDeck().get(0));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(19, player.getPlayDeck().get(1));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(26, player.getPlayDeck().get(2));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(33, player.getPlayDeck().get(3));

        Mockito.verify(barViewer, Mockito.times(1))
                .drawHealthBar(15, 21, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawManaBar(15, 22, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawPointBar(15, 12, 0, 0);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(20, 11, "Drawing...");

        player.setTurnOver(true);
        viewer.drawPlayer(player);

        Mockito.verify(graphics, Mockito.times(1))
                .putString(20, 11, "Finished!");
    }

    @Test
    public void testDrawEnemy(){
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));
        BarSet barSet = Mockito.mock(BarSet.class);

        GameParticipant enemy = new GameParticipant(player_special, barSet);
        enemy.setDrawDeck(new ArrayList<>());

        GameParticipantViewer viewer = new GameParticipantViewer(barViewer, cardViewer, graphics);
        viewer.drawEnemy(enemy);

        Mockito.verify(cardViewer, Mockito.times(1))
                .drawCard(1, 0, 0);

        Mockito.verify(barViewer, Mockito.times(1))
                .drawHealthBar(15, 1, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawManaBar(15, 2, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawPointBar(15, 8, 0, 12);
        Mockito.verify(graphics, Mockito.times(1))
                .putString(20, 7, "Drawing...");

        enemy.setTurnOver(true);
        viewer.drawPlayer(enemy);

        Mockito.verify(graphics, Mockito.times(1))
                .putString(20, 11, "Finished!");
    }
}
