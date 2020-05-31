package com.g13.view.arena;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class ComponentFactory {
    private CardViewer cardViewer;
    private BarViewer barViewer;
    private GameParticipantViewer gameParticipantViewer;

    private TextGraphics graphics;

    public ComponentFactory(TerminalScreen screen){
        graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        this.cardViewer = new CardViewer(screen.newTextGraphics());
        this.barViewer = new BarViewer(screen.newTextGraphics(), screen.newTextGraphics(), screen.newTextGraphics());
        this.gameParticipantViewer = new GameParticipantViewer(barViewer, cardViewer, graphics);
    }

    public CardViewer getCardViewer() { return cardViewer; }
    public BarViewer getBarViewer() { return barViewer; }
    public GameParticipantViewer getGameParticipantViewer() { return gameParticipantViewer; }
    public TextGraphics getGraphics() { return graphics; }
}
