package com.g13.controller.arena;

import com.g13.controller.Controller;
import com.g13.controller.arena.activationfactory.ActivationFactory;
import com.g13.controller.arena.commands.*;
import com.g13.controller.arena.strategies.PlayStrategy;
import com.g13.controller.state.StateRecognizer;
import com.g13.model.arena.Arena;
import com.g13.model.arena.Enemy;
import com.g13.model.arena.GameParticipant;
import com.g13.model.arena.specialcards.SpecialCard;
import com.g13.view.arena.ArenaViewer;

import java.io.IOException;
import java.util.List;

import static java.lang.Integer.min;

public class ArenaController implements Controller {
    private Arena model;
    private ArenaViewer view;
    private StateRecognizer recognizer;
    private ParticipantController playerController;
    private ParticipantController enemyController;
    private ActivationFactory activationFactory;

    private int cmdStage = 0;


    public ArenaController(ArenaViewer arenaViewer, Arena arena, StateRecognizer recognizer){
        this.view = arenaViewer;
        this.model = arena;
        this.recognizer = recognizer;
        activationFactory = new ActivationFactory();
    }

    @Override
    public void start() throws IOException {
        if (verifyEndOfGame()) return;

        ArenaViewer.COMMAND command = view.getNextCommand();
        CommandParser parser = new CommandParser(this);
        for (Command cmd : parser.parse(command))
            cmd.execute();
    }

    @Override
    public void render() throws IOException {
        view.draw();
    }

    @Override
    public boolean isFinished() {
        return model.isFinished();
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

    public boolean endOfRound(){
        return model.getPlayer().getTurnOver() && model.getEnemy().getTurnOver();
    }

    public ParticipantController getLoser(){
        return enemyController.getPoints() == 0 ? enemyController : playerController;
    }

    public ParticipantController getWinner(){
        return enemyController.getPoints() != 0 ? enemyController : playerController;
    }

    public void resetRound(){
        playerController.setTurnOver(false);
        enemyController.setTurnOver(false);
        processPlayerCards(playerController);
        model.setPlayersTurn(false);
        processPlayerCards(enemyController);
        model.setPlayersTurn(true);
        playerController.setPoints(0);
        enemyController.setPoints(0);
    }

    public void checkControllerPoints(){
        ParticipantController current = getCurrent();
        ParticipantController opposite = getOpponent();

        if(current.getPoints() == current.getMaxPoints())
            current.setTurnOver(true);

        if(current.getPoints() > current.getMaxPoints()){
            int a = min(current.getPoints() - 1, 6);
            a = min(a, opposite.getPoints() - 1);
            if(a < 0)  a = 0;

            current.setPoints(a);
            current.setTurnOver(true);
            opposite.setTurnOver(true);
        }
    }

    public void processPlayerCards(ParticipantController controller){
        List<SpecialCard> deck = controller.getParticipant().getActiveCards();
        for(int i = 0; i < deck.size(); i++){
            activationFactory.getEndOfTurnActivation(deck.get(i)).activateEndOfTurn(this);
            if(deck.get(i).getRoundsLeft() <= 0)
                deck.remove(i--);
        }
        controller.getParticipant().setActiveCards(deck);
    }

    public ParticipantController getCurrent(){
        return model.getPlayersTurn() ? getPlayerController() : getEnemyController();
    }

    public ParticipantController getOpponent(){
        return model.getPlayersTurn() ? getEnemyController() : getPlayerController();
    }

    public ActivationFactory getActivationFactory() { return activationFactory; }

    private boolean verifyEndOfGame() {
        if (playerController.getHealth() <= 0 || enemyController.getHealth() <= 0) {
            recognizer.getCurrentState().advance();
            return true;
        }
        else return false;
    }

    public int getCmdStage() { return cmdStage; }
    public void advanceCmdStage() { cmdStage = (cmdStage+1) % 2; }

    public void setEnemyStrategy(PlayStrategy strategy){
        model.getEnemy().setPlayStrategy(strategy);
    }
}
