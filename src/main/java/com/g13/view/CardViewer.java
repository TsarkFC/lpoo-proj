package com.g13.view;

import com.g13.model.GameParticipant;
import com.g13.model.SpecialCard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class CardViewer {
    private TextGraphics graphics;

    public CardViewer(TextGraphics graphics){
        this.graphics = graphics;
    }

    public void drawCard(int x, int y, int deck_size){
        setGraphics("#EECC88", "#FF0000");
        drawCardStructure(x, y);
        graphics.putString(x + 2, y + 1, String.valueOf(deck_size));
    }

    public void drawSpecialCard(int x, SpecialCard specialCard){
        setGraphics("#EECC88", "#FF0000");
        drawCardStructure(x, 15);
        graphics.putString(x + 2, 16, String.valueOf(specialCard.getCost()));
        graphics.putString(x + 2, 17, String.valueOf(specialCard.getSymbol()));
    }

    public void drawCardInfo(int cardno, TerminalScreen screen, GameParticipant player) throws IOException {
        setGraphics("#336699", "#FFFFFF");
        graphics.putString(20, 24, "Card Info:");
        graphics.putString(20, 25, player.getCardInfo(cardno));
        screen.refresh();
    }

    private void drawCardStructure(int x, int y){
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 3; j++)
                graphics.putString(x+i, y+j, " ");
    }

    private void setGraphics(String back, String fore){
        graphics.setBackgroundColor(TextColor.Factory.fromString(back));
        graphics.setForegroundColor(TextColor.Factory.fromString(fore));
    }
}
