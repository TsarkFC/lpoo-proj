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
            int select = -1;

            Gui.COMMAND command = view.getNextCommand();
            if (command == Gui.COMMAND.ONE) select = 0;
            else if(command == Gui.COMMAND.TWO) select = 1;
            else if(command == Gui.COMMAND.THREE) select = 2;
            else if(command == Gui.COMMAND.FOUR) select = 3;
            if (select != -1) playerController.setCardSelected(select, true);

            if (command == Gui.COMMAND.SWITCH) {
                if (endOfRound())
                    interStageHandler();
                else {
                    SkipTurnCommand skipTurnCommand = new SkipTurnCommand(playerController);
                    skipTurnCommand.execute();
                }
            }

            if (command == Gui.COMMAND.DRAW){
                if(!model.getPlayer().getTurnOver()) {
                    DrawCardCommand drawCmd = new DrawCardCommand(this);
                    drawCmd.execute();
                }
                if(!model.getEnemy().getTurnOver())
                    playEnemyTurn();
            }

            if(!model.getPlayer().getTurnOver()) {
                int cardno = 0;
                if (command == Gui.COMMAND.PLAYCARD1) cardno = 1;
                else if (command == Gui.COMMAND.PLAYCARD2) cardno = 2;
                else if (command == Gui.COMMAND.PLAYCARD3) cardno = 3;
                else if (command == Gui.COMMAND.PLAYCARD4) cardno = 4;

                if (cardno != 0){
                    PlaySpecialCardCommand specialCardCommand = new PlaySpecialCardCommand(cardno, this, playerController, enemyController);
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
        model.setPlayersTurn(false);
        PlayStrategy strategy = getEnemy().getPlayStrategy();
        if (!strategy.playTurn(this)) {
            SkipTurnCommand skipTurnCommand = new SkipTurnCommand(enemyController);
            skipTurnCommand.execute();
        }
        model.setPlayersTurn(true);
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

    public void checkControllerPoints(){
        ParticipantController current = getCurrent();
        ParticipantController opposite = getOpponent();

        if(current.getPoints() == current.getMax_points()){
            current.setTurnOver(true);
        }

        if(current.getPoints() > current.getMax_points()){
            int a = min(current.getPoints() - 1, 6);
            a = min(a, opposite.getPoints() - 1);
            if(a < 0)  a = 0;

            current.setPoints(a);
            current.setTurnOver(true);
            opposite.setTurnOver(true);
        }
    }

    public void ProcessPlayerCards(SpecialCard.ACTIVATION_CONDITIONS activationConditions){
        List<SpecialCard> a = playerController.getParticipant().getActiveCards();
        for(int i = 0; i < a.size(); i++){
            a.get(i).activate(activationConditions, this);
            if(a.get(i).getRoundsLeft() <= 0)
                a.remove(i);
        }
        playerController.getParticipant().setActiveCards(a);
    }

    public void ProcessEnemyCards(SpecialCard.ACTIVATION_CONDITIONS activationConditions){
        for(int i = 0; i < enemyController.getParticipant().getActiveCards().size(); i++){
            enemyController.getParticipant().getActiveCards().get(i)
                    .activate(activationConditions, this);
        }
    }

    public ParticipantController getCurrent(){
        if (model.getPlayersTurn()) return getPlayerController();
        return getEnemyController();
    }

    public ParticipantController getOpponent(){
        if (model.getPlayersTurn()) return getEnemyController();
        return getPlayerController();
    }
}
