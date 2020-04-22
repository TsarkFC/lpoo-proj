package View;

import Model.Player;
import Model.SpecialCard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class CardViewer {

    public void drawCard(int x, int y, int deck_size, TerminalScreen screen){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        drawCardStructure(x, y, graphics);
        graphics.putString(x + 2, y + 1, String.valueOf(deck_size));

    }

    public void drawSpecialCard(int x, SpecialCard specialCard, TerminalScreen screen){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        drawCardStructure(x, 15, graphics);

        TextGraphics pointNumber = screen.newTextGraphics();
        pointNumber.setBackgroundColor(TextColor.Factory.fromString("#EECC88"));
        pointNumber.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        pointNumber.putString(x + 2, 16, String.valueOf(specialCard.getCost()));
        pointNumber.putString(x+2, 17, String.valueOf(specialCard.getSymbol()));
    }

    private void drawCardStructure(int x, int y, TextGraphics graphics){ //TODO: Make iterative approach
        graphics.putString(x + 1, y + 3, " ");
        graphics.putString(x + 2, y + 3, " ");
        graphics.putString(x + 3, y + 3, " ");
        graphics.putString(x + 1, y, " ");
        graphics.putString(x + 2, y, " ");
        graphics.putString(x + 3, y, " ");
        graphics.putString(x, y + 3, " ");
        graphics.putString(x, y + 2, " ");
        graphics.putString(x, y + 1, " ");
        graphics.putString(x + 4, y + 3, " ");
        graphics.putString(x + 4, y + 2, " ");
        graphics.putString(x + 4, y + 1, " ");
        graphics.putString(x + 5, y + 3, " ");
        graphics.putString(x + 5, y + 2, " ");
        graphics.putString(x + 5, y + 1, " ");
        graphics.putString(x + 5, y, " ");

        graphics.putString(x + 1, y + 1, " ");
        graphics.putString(x + 1, y + 2, " ");
        graphics.putString(x + 2, y + 1, " "); //No need for this one, the number always covers it;
        graphics.putString(x + 2, y + 2, " ");
        graphics.putString(x + 3, y + 1, " ");
        graphics.putString(x + 3, y + 2, " ");


        graphics.putString(x, y, " ");
        graphics.putString(x+4, y, " ");
    }

    public void drawCardInfo(int cardno, TerminalScreen screen, Player player) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(20, 24, "Card Info:");
        graphics.putString(20, 25, (player.getPlay_deck().get(cardno)).getCardInfo());
        screen.refresh();
    }

}
