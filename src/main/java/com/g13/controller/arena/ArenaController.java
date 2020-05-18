package com.g13.controller.arena;

import com.g13.controller.Controller;
import com.g13.controller.arena.activationfactory.ActivationFactory;
import com.g13.controller.arena.commands.*;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.model.arena.Arena;
import com.g13.model.arena.Enemy;
import com.g13.model.arena.GameParticipant;
import com.g13.controller.arena.observer.ArenaObserver;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.view.arena.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class ArenaController implements Controller {
    private Arena model;
    private ArenaViewer view;
    private ParticipantController playerController;
    private ParticipantController enemyController;
    private ActivationFactory activationFactory;
    private List<Command> commands;
    private int cmdStage = 0;


    public ArenaController(ArenaViewer arenaViewer, Arena arena){
        this.view = arenaViewer;
        this.model = arena;
        commands = new ArrayList<>();
        commands.add(new PointDiffCommand(this));
        commands.add(new ApplyDiffCommand(this));
        activationFactory = new ActivationFactory();
    }

    public void start() throws IOException {
        view.draw();

        while(!model.isFinished()){
            playerController.resetCardSelection();
            int select = -1;

            ArenaViewer.COMMAND command = view.getNextCommand();
            if (command == ArenaViewer.COMMAND.ONE) select = 0;
            else if(command == ArenaViewer.COMMAND.TWO) select = 1;
            else if(command == ArenaViewer.COMMAND.THREE) select = 2;
            else if(command == ArenaViewer.COMMAND.FOUR) select = 3;
            if (select != -1) playerController.setCardSelected(select, true);

            if (command == ArenaViewer.COMMAND.SWITCH) {
                if (endOfRound())
                    interStageHandler();
                else
                    new SkipTurnCommand(playerController).execute();
            }

            if (command == ArenaViewer.COMMAND.DRAW){
                if(!model.getPlayer().getTurnOver())
                    new DrawCardCommand(this).execute();
                if(!model.getEnemy().getTurnOver())
                    playEnemyTurn();
            }

            if(!model.getPlayer().getTurnOver()) {
                int cardno = 0;
                if (command == ArenaViewer.COMMAND.PLAYCARD1) cardno = 1;
                else if (command == ArenaViewer.COMMAND.PLAYCARD2) cardno = 2;
                else if (command == ArenaViewer.COMMAND.PLAYCARD3) cardno = 3;
                else if (command == ArenaViewer.COMMAND.PLAYCARD4) cardno = 4;

                if (cardno != 0)
                    new PlaySpecialCardCommand(cardno, this).execute();
            }

            notifyObservers();

            if (command == ArenaViewer.COMMAND.QUIT)
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
        cmdStage++;
        if (cmdStage == commands.size()) {
            resetRound();
            cmdStage = 0;
        }
    }

    private void resetRound(){
        playerController.setTurnOver(false);
        enemyController.setTurnOver(false);
        playerController.setPoints(0);
        enemyController.setPoints(0);
        ProcessPlayerCards(SpecialCard.ACTIVATION_CONDITIONS.ON_END_TURN);
        model.setPlayersTurn(false);
        ProcessEnemyCards(SpecialCard.ACTIVATION_CONDITIONS.ON_END_TURN);
        model.setPlayersTurn(true);
    }

    public void notifyObservers() throws IOException {
        for (ArenaObserver observer : model.getObservers()) {
            observer.arenaChanged();
        }
    }

    public void checkControllerPoints(){
        ParticipantController current = getCurrent();
        ParticipantController opposite = getOpponent();

        if(current.getPoints() == current.getMaxPoints()){
            current.setTurnOver(true);
        }

        if(current.getPoints() > current.getMaxPoints()){
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
            activationFactory.getActivation(a.get(i)).activate(activationConditions, this);
            if(a.get(i).getRoundsLeft() <= 0)
                a.remove(i);
        }
        playerController.getParticipant().setActiveCards(a);
    }

    public void ProcessEnemyCards(SpecialCard.ACTIVATION_CONDITIONS activationConditions){
        for(int i = 0; i < enemyController.getParticipant().getActiveCards().size(); i++){
            System.out.println("Old hp: " + enemyController.getParticipant().getHealth());
            activationFactory.getActivation(enemyController.getParticipant().getActiveCards().get(i))
                .activate(activationConditions, this);
            System.out.println("New hp: " + enemyController.getParticipant().getHealth());
        }
    }

    public ParticipantController getCurrent(){
        return model.getPlayersTurn() ? getPlayerController() : getEnemyController();
    }

    public ParticipantController getOpponent(){
        return model.getPlayersTurn() ? getEnemyController() : getPlayerController();
    }

    public ActivationFactory getActivationFactory() { return activationFactory; }
}
