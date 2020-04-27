package controller;

import commands.DrawCardCommand;
import model.Arena;
import model.GameParticipant;
import view.Gui;

import java.io.IOException;

public class ArenaController {
    private Arena model;
    private Gui view;
    private GameParticipantController playerController;
    private GameParticipantController enemyController;

    public ArenaController(Gui gui, Arena arena){
        this.view = gui;
        this.model = arena;
    }

    public void start() throws IOException {
        view.draw();

        while(!model.isFinished()){
            Gui.COMMAND command = view.getNextCommand();
            if (command == Gui.COMMAND.SWITCH)
                model.switchPlayer();

            if (command == Gui.COMMAND.DRAW){
                DrawCardCommand drawCmd = new DrawCardCommand(this, playerController, enemyController);
                drawCmd.execute();
                model.notifyObservers();
            }
            if (command == Gui.COMMAND.QUIT){
                model.finish();
            }
        }
    }

    public int getWidth(){ return model.getWidth(); }
    public int getHeight(){ return model.getHeight(); }

    public GameParticipant getPlayer() {return model.getPlayer();}
    public GameParticipant getEnemy() {return model.getEnemy();}
    public Arena getModel() {return model;}

    public boolean getCurrent() {return model.getCurrent();}

    public GameParticipantController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(GameParticipant player) {
        this.playerController = new GameParticipantController(player);
        model.setPlayer(player);
        playerController.setDefaultDeck();
    }

    public GameParticipantController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(GameParticipant enemy) {
        this.enemyController = new GameParticipantController(enemy);
        model.setEnemy(enemy);
        enemyController.setDefaultDeck();
    }

    public void playEnemyTurn(){ //TODO: Complete playTurn function with proper-ish AI
        if(getEnemy().getPoints() < 8){
            DrawCardCommand command = new DrawCardCommand(this, enemyController, playerController);
            command.execute();
        }
    }
}
