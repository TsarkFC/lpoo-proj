package com.g13.controller;

import com.g13.controller.commands.*;
import com.g13.controller.strategies.PlayStrategy;
import com.g13.model.Arena;
import com.g13.model.Enemy;
import com.g13.model.GameParticipant;
import com.g13.controller.observer.ArenaObserver;
import com.g13.model.SpecialCardTypes.SpecialCard;
import com.g13.view.Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class ArenaController {
    private Arena model;
    private Gui view;
    private ParticipantController playerController;
    private ParticipantController enemyController;
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
            playerController.resetCardSelection();

            Gui.COMMAND command = view.getNextCommand();
            if (command == Gui.COMMAND.ONE)
                playerController.setCardSelected(0, true);
            else if(command == Gui.COMMAND.TWO)
                playerController.setCardSelected(1, true);
            else if(command == Gui.COMMAND.THREE)
                playerController.setCardSelected(2, true);
            else if(command == Gui.COMMAND.FOUR)
                playerController.setCardSelected(3, true);

            if (command == Gui.COMMAND.SWITCH) {
                if (endOfRound()){
                    interStageHandler();
                }
                else {
                    SkipTurnCommand skipTurnCommand = new SkipTurnCommand(playerController);
                    skipTurnCommand.execute();
                }
            }
            if (command == Gui.COMMAND.DRAW){
                if(!model.getPlayer().getTurnOver()) {
                    DrawCardCommand drawCmd = new DrawCardCommand(this, playerController, enemyController);
                    drawCmd.execute();
                }
                if(!model.getEnemy().getTurnOver())
                    playEnemyTurn();
            }

            if(!model.getPlayer().getTurnOver()) {
                if (command == Gui.COMMAND.PLAYCARD1) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(1, this, playerController, enemyController);
                    specialCardCommand.execute();
                }
                if (command == Gui.COMMAND.PLAYCARD2) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(2, this, playerController, enemyController);
                    specialCardCommand.execute();
                }
                if (command == Gui.COMMAND.PLAYCARD3) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(3, this, playerController, enemyController);
                    specialCardCommand.execute();
                }
                if (command == Gui.COMMAND.PLAYCARD4) {
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(4, this, playerController, enemyController);
                    specialCardCommand.execute();
                }
            }

            notifyObservers();

            if (command == Gui.COMMAND.QUIT)
                model.finish();
        }
    }

    public GameParticipant getPlayer() {return model.getPlayer();}
    public Enemy getEnemy() {return model.getEnemy();}
    public Arena getModel() {return model;}

    public ParticipantController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(GameParticipant player) {
        this.playerController = new ParticipantController(player);
        model.setPlayer(player);
        playerController.setDefaultDeck();
    }

    public ParticipantController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(Enemy enemy) {
        this.enemyController = new ParticipantController(enemy);
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

    public ParticipantController getLooser(){
        return enemyController.getPoints() == 0 ? enemyController : playerController;
    }

    public ParticipantController getWinner(){
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
        ProcessPlayerCards(SpecialCard.ACTIVATION_CONDITIONS.ON_END_TURN);
        ProcessEnemyCards(SpecialCard.ACTIVATION_CONDITIONS.ON_END_TURN);
    }

    public void notifyObservers() throws IOException {
        for (ArenaObserver observer : model.getObservers()) {
            observer.arenaChanged();
        }
    }

    public void checkControllerPoints(ParticipantController playerController, ParticipantController enemyController){
        if(playerController.getPoints() == playerController.getMax_points()){
            playerController.setTurnOver(true);
        }

        if(playerController.getPoints() > playerController.getMax_points()){
            int a = min(this.getPlayer().getPoints() - 1, 6);
            a = min(a, this.getEnemy().getPoints() - 1);
            if(a < 0)  a = 0;

            playerController.setPoints(a);
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            playerController.setTurnOver(true);
            enemyController.setTurnOver(true);
        }
    }

    public void ProcessPlayerCards(SpecialCard.ACTIVATION_CONDITIONS activationConditions){
        List<SpecialCard> a = playerController.getParticipant().getActiveCards();
        for(int i = 0; i < a.size(); i++){
            a.get(i).activate(activationConditions, this, playerController, enemyController);
            if(a.get(i).getRoundsLeft() <= 0)
                a.remove(i);
        }
        playerController.getParticipant().setActiveCards(a);
    }

    public void ProcessEnemyCards(SpecialCard.ACTIVATION_CONDITIONS activationConditions){
        for(int i = 0; i < enemyController.getParticipant().getActiveCards().size(); i++){
            enemyController.getParticipant().getActiveCards().get(i)
                    .activate(activationConditions, this, enemyController, playerController);
        }
    }
}
