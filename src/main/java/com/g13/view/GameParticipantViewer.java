package com.g13.view;

import com.g13.model.GameParticipant;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameParticipantViewer {
    private BarViewer barViewer;
    private CardViewer cardViewer;
    private TextGraphics graphics;

    public GameParticipantViewer(BarViewer barViewer, CardViewer cardViewer, TextGraphics graphics){
        this.cardViewer = cardViewer;
        this.barViewer = barViewer;
        this.graphics = graphics;
    }

    public void drawPlayer(GameParticipant player){
        cardViewer.drawCard(1, 19, player.getDrawDeck().size());
        barViewer.drawHealthBar(15, 21, player.getHealth(), player.getMaxHealth());
        barViewer.drawManaBar(15, 22, player.getMana(), player.getMaxMana());
        barViewer.drawPointBar(15, 12, player.getPoints(), player.getMaxPoints());
        drawPlayerHand(player);
        drawPlayingState(player, 20, 11);
    }

    private void drawPlayerHand(GameParticipant player){
        for (int i = 0; i < 4*7; i+=7){
            if (i/7 >= player.getPlayDeck().size()) break;
            cardViewer.drawSpecialCard(i+12, player.getPlayDeck().get(i/7));
        }
    }

    private void drawPlayingState(GameParticipant player, int x, int y){
        if (player.getTurnOver())
            graphics.putString(x, y, "Finished!");
        else
            graphics.putString(x, y, "Drawing...");
    }

    public void drawEnemy(GameParticipant enemy){
        cardViewer.drawCard(1, 0, enemy.getDrawDeck().size());
        barViewer.drawHealthBar(15, 1, enemy.getHealth(), enemy.getMaxHealth());
        barViewer.drawManaBar(15, 2, enemy.getMana(), enemy.getMaxMana());
        barViewer.drawPointBar(15, 8, enemy.getPoints(), 12);
        drawPlayingState(enemy, 20, 7);
    }
}
