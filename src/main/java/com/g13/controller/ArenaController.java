package com.g13.controller;

import com.g13.controller.commands.*;
import com.g13.controller.strategies.PlayStrategy;
import com.g13.model.Arena;
import com.g13.model.Enemy;
import com.g13.model.GameParticipant;
import com.g13.controller.observer.ArenaObserver;
import com.g13.view.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.min;

public class ArenaController {
    private Arena model;
    private Gui view;
    private GameParticipantController playerController;
    private GameParticipantController enemyController;
    private List<Command> commands;
    private int cmdStage = 0;

    public ArenaController(Gui gui, Arena arena){
        this.view = gui;
        this.model = arena;
        commands = new ArrayList<>();
        commands.add(new PointDiffCommand(this));
        commands.add(new ApplyDiffCommand(this));
    }

    public void start() throws IOException {
        view.draw();

        while(!model.isFinished()){
            Gui.COMMAND command = view.getNextCommand();
            if (command == Gui.COMMAND.SWITCH) {
                if (endOfRound()){
                    interStageHandler();
                }
                else {
                    SkipTurnCommand skipTurnCommand = new SkipTurnCommand(playerController);
                    skipTurnCommand.execute();
                }

                notifyObservers();
            }
            if (command == Gui.COMMAND.DRAW){
                if(!model.getPlayer().getTurnOver()) {
                    DrawCardCommand drawCmd = new DrawCardCommand(playerController, enemyController);
                    drawCmd.execute();
                }
                if(!model.getEnemy().getTurnOver())
                    playEnemyTurn();

                notifyObservers();
            }
            if (command == Gui.COMMAND.QUIT)
                model.finish();
            if(!model.getPlayer().getTurnOver()) {
                if (command == Gui.COMMAND.PLAYCARD1) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(1, this, playerController, enemyController);
                    specialCardCommand.execute();
                    notifyObservers();
                }
                if (command == Gui.COMMAND.PLAYCARD2) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(2, this, playerController, enemyController);
                    specialCardCommand.execute();
                    notifyObservers();
                }
                if (command == Gui.COMMAND.PLAYCARD3) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(3, this, playerController, enemyController);
                    specialCardCommand.execute();
                    notifyObservers();
                }
                if (command == Gui.COMMAND.PLAYCARD4) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(4, this, playerController, enemyController);
                    specialCardCommand.execute();
                    notifyObservers();
                }
            }
        }
    }

    public GameParticipant getPlayer() {return model.getPlayer();}
    public Enemy getEnemy() {return model.getEnemy();}
    public Arena getModel() {return model;}

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

    public void playEnemyTurn(){
        PlayStrategy strategy = getEnemy().getPlayStrategy();
        if (!strategy.playTurn(this)) {
            //Acabar a ronda do inimigo
            SkipTurnCommand skipTurnCommand = new SkipTurnCommand(enemyController);
            skipTurnCommand.execute();
        }
    }

    private boolean endOfRound(){
        return model.getPlayer().getTurnOver() && model.getEnemy().getTurnOver();
    }

    public GameParticipantController getLooser(){
        return enemyController.getPoints() == 0 ? enemyController : playerController;
    }

    public GameParticipantController getWinner(){
        return enemyController.getPoints() != 0 ? enemyController : playerController;
    }

    private void interStageHandler(){
        commands.get(cmdStage).execute();
        if (cmdStage != 1 && cmdStage != 0){
           commands.remove(cmdStage);
        }
        else{
            cmdStage++;
            if (cmdStage == commands.size()) {
                resetRound();
                cmdStage = 0;
            }
        }
    }

    private void resetRound(){
        playerController.setTurnOver(false);
        enemyController.setTurnOver(false);
        playerController.setPoints(0);
        enemyController.setPoints(0);
    }

    public void notifyObservers() {
        for (ArenaObserver observer : model.getObservers()) {
            observer.arenaChanged();
        }
    }

    public void checkControllerPoints(GameParticipantController participantController){
        if(participantController.getPoints() == participantController.getMax_points()){
            participantController.setTurnOver(true);
        }

        if(participantController.getPoints() > participantController.getMax_points()){
            int a = min(this.getPlayer().getPoints() - 1, 6);
            a = min(a, this.getEnemy().getPoints() - 1);
            if(a < 0){
                a = 0;
            }
            participantController.setPoints(a);
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            participantController.setTurnOver(true);
            participantController.setTurnOver(true);
        }
    }
}
