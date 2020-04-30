package view;

import model.GameParticipant;

public class GameParticipantViewer {
    private BarViewer barViewer;
    private CardViewer cardViewer;

    public GameParticipantViewer(BarViewer barViewer, CardViewer cardViewer){
        this.cardViewer = cardViewer;
        this.barViewer = barViewer;
    }

    public void drawPlayer(GameParticipant player){
        cardViewer.drawCard(1, 19, player.getDraw_deck().size());
        barViewer.drawHealthBar(15, 21, player.getHealth(), player.getMaxHealth());
        barViewer.drawManaBar(15, 22, player.getMana(), player.getMaxMana());
        barViewer.drawPointBar(15, 12, player.getPoints(), 12);

        for (int i = 0; i < 4*7 && i/7 < player.getPlay_deck().size(); i+=7)
            cardViewer.drawSpecialCard(i+12, player.getPlay_deck().get(i/7));
    }

    public void drawEnemy(GameParticipant enemy){
        cardViewer.drawCard(1, 0, enemy.getDraw_deck().size());
        barViewer.drawHealthBar(15, 1, enemy.getHealth(), enemy.getMaxHealth());
        barViewer.drawManaBar(15, 2, enemy.getMana(), enemy.getMaxMana());
        barViewer.drawPointBar(15, 8, enemy.getPoints(), 12);
    }
}
