package view;

import model.GameParticipant;
import model.SpecialCard;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewerTest {
    @Test
    public void testDrawPlayer(){
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        GameParticipant player = new GameParticipant(player_special, 0, 0, 0, 0, 0);
        player.setDraw_deck(new ArrayList<>());

        BarViewer barViewer = Mockito.mock(BarViewer.class);
        CardViewer cardViewer = Mockito.mock(CardViewer.class);

        GameParticipantViewer viewer = new GameParticipantViewer(barViewer, cardViewer);
        viewer.drawPlayer(player);

        Mockito.verify(cardViewer, Mockito.times(1))
                .drawCard(1, 19, 0);
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(12, player.getPlay_deck().get(0));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(19, player.getPlay_deck().get(1));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(26, player.getPlay_deck().get(2));
        Mockito.verify(cardViewer, Mockito.times(1))
                .drawSpecialCard(33, player.getPlay_deck().get(3));

        Mockito.verify(barViewer, Mockito.times(1))
                .drawHealthBar(15, 21, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawManaBar(15, 22, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawPointBar(15, 12, 0, 12);
    }

    @Test
    public void testDrawEnemy(){
        List<SpecialCard> player_special = new ArrayList<>();
        player_special.add(new SpecialCard(2, '*', "card no 2"));
        player_special.add(new SpecialCard(7, '+', "card no 7"));
        player_special.add(new SpecialCard(4, '-', "card no 4"));
        player_special.add(new SpecialCard(5, '/', "card no 5"));

        GameParticipant enemy = new GameParticipant(player_special, 0, 0, 0, 0, 0);
        enemy.setDraw_deck(new ArrayList<>());

        BarViewer barViewer = Mockito.mock(BarViewer.class);
        CardViewer cardViewer = Mockito.mock(CardViewer.class);

        GameParticipantViewer viewer = new GameParticipantViewer(barViewer, cardViewer);
        viewer.drawEnemy(enemy);

        Mockito.verify(cardViewer, Mockito.times(1))
                .drawCard(1, 0, 0);

        Mockito.verify(barViewer, Mockito.times(1))
                .drawHealthBar(15, 1, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawManaBar(15, 2, 0, 0);
        Mockito.verify(barViewer, Mockito.times(1))
                .drawPointBar(15, 8, 0, 12);
    }
}
