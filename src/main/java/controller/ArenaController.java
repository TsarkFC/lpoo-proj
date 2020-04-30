package controller;

import commands.DrawCardCommand;
import controller.strategies.NormalPlayStrategy;
import controller.strategies.PlayStrategy;
import model.Arena;
import model.GameParticipant;
import observer.ArenaObserver;
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
                model.setCurrent(false);

            if (command == Gui.COMMAND.DRAW){
                DrawCardCommand drawCmd = new DrawCardCommand(this, playerController, enemyController);
                drawCmd.execute();
                notifyObservers();
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

    public void playEnemyTurn(){ //TODO: Un hard-code the PlayStrategy

        //DONE: Complete playTurn function with proper-ish AI - Done with strategy design pattern
        PlayStrategy strategy = new NormalPlayStrategy();
        strategy.playTurn(this);
    }

    public void notifyObservers() {
        for (ArenaObserver observer : model.getObservers()) {
            observer.arenaChanged();
        }
    }
}
