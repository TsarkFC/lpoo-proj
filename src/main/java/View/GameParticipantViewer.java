package View;

import Model.Enemy;
import Model.Player;
import com.googlecode.lanterna.screen.TerminalScreen;

public class GameParticipantViewer {
    private TerminalScreen screen;
    private BarViewer barViewer;
    private CardViewer cardViewer;

    public GameParticipantViewer(TerminalScreen screen, CardViewer cardViewer){
        this.screen = screen;
        this.cardViewer = cardViewer;
        this.barViewer = new BarViewer(screen);
    }

    public void drawPlayer(Player player){
        cardViewer.drawCard(1, 19, player.getDraw_deck().size(), screen);
        barViewer.drawHealthBar(15, 21, player.getHealth(), player.getMaxHealth());
        barViewer.drawManaBar(15, 22, player.getMana(), player.getMaxMana());
        barViewer.drawPointBar(15, 12, player.getPoints(), 12);

        cardViewer.drawSpecialCard(12, player.getPlay_deck().get(0), screen);
        cardViewer.drawSpecialCard(19, player.getPlay_deck().get(1), screen);
        cardViewer.drawSpecialCard(26, player.getPlay_deck().get(2), screen);
        cardViewer.drawSpecialCard(33, player.getPlay_deck().get(3), screen);
    }

    public void drawEnemy(Enemy enemy){
        cardViewer.drawCard(1, 0, enemy.getDraw_deck().size(), screen);
        barViewer.drawHealthBar(15, 1, enemy.getHealth(), enemy.getMaxHealth());
        barViewer.drawManaBar(15, 2, enemy.getMana(), enemy.getMaxMana());
        barViewer.drawPointBar(15, 8, enemy.getPoints(), 12);
    }
}
