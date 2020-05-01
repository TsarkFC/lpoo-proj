package com.g13.controller;

import com.g13.controller.strategies.PlayStrategy;
import com.g13.controller.commands.DrawCardCommand;
import com.g13.model.Arena;
import com.g13.model.Enemy;
import com.g13.model.GameParticipant;
import com.g13.observer.ArenaObserver;
import com.g13.view.Gui;

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
            if (command == Gui.COMMAND.SWITCH) {
                model.getPlayer().setTurnOver(true);
            }

            if (command == Gui.COMMAND.DRAW){
                if(!model.getPlayer().getTurnOver()) {
                    DrawCardCommand drawCmd = new DrawCardCommand(this, playerController, enemyController);
                    drawCmd.execute();
                }
                //TODO: Use turn_over variables to check if they player's turn is over (using a command?)
                if(!this.getModel().getEnemy().getTurnOver()) {

                    this.playEnemyTurn();
                }

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
    public Enemy getEnemy() {return model.getEnemy();}
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

    public void setEnemyController(Enemy enemy) {
        this.enemyController = new GameParticipantController(enemy);
        model.setEnemy(enemy);
        enemyController.setDefaultDeck();
    }

    public void playEnemyTurn(){ //DONE: Un hard-code the PlayStrategy

        //if(model.getEnemy().getTurnOver() == false) {
            //DONE: Complete playTurn function with proper-ish AI - Done with strategy design pattern
            PlayStrategy strategy = getEnemy().getPlayStrategy();
            if (!strategy.playTurn(this)) {
                //Acabar a ronda do inimigo
                model.getEnemy().setTurnOver(true);
            }
        //}

    }

    public void notifyObservers() {
        for (ArenaObserver observer : model.getObservers()) {
            observer.arenaChanged();
        }
    }
}