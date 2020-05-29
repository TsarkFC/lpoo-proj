package com.g13.view.arena;

import com.g13.model.arena.specialcards.SpecialCard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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
        int offset = 0;
        if (!specialCard.getSelected())
            setGraphics("#EECC88", "#FF0000");
        else {
            offset = 1;
            drawCardInfo(specialCard.getCardInfo());
            setGraphics("#AA8855", "#FF0000");
        }

        drawCardStructure(x, 15 - offset);
        graphics.putString(x + 2, 16 - offset, String.valueOf(specialCard.getCost()));
        graphics.putString(x + 2, 17 - offset, String.valueOf(specialCard.getSymbol()));
    }

    private void drawCardInfo(String info){
        setGraphics("#336699", "#FFFFFF");
        graphics.putString(20, 24, "Card Info:");
        graphics.putString(1, 25, info);
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
