package model;

import commands.ArenaObserver;
import controller.GameParticipantController;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Player player;   //to fix
    private Enemy enemy;     //to fix
    private boolean current; //to fix  true->player | false -> enemy

    private boolean isFinished;
    private List<ArenaObserver> observers;

    private GameParticipantController playerController;
    private GameParticipantController enemyController;

    public Arena(Player player, Enemy enemy, int width, int height){
        this.player = player;
        this.enemy = enemy;
        this.width = width;
        this.height = height;
        this.current = true;
        this.isFinished = false;
        this.observers = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void finish() {
        this.isFinished = true;
    }

    public void addObserver(ArenaObserver observer) {
        observers.add(observer);
    }

    public void switchPlayer(){ //->Current podia passar a ser um GameParticipant em vez de boolean (evitava verificações de if(current))
        if (!current) return; //-> Possibly print a message saying that enemy is playing its turn
        current = false;
    }

    public void notifyObservers() {
        for (ArenaObserver observer : observers) {
            observer.arenaChanged();
        }
    }

    public boolean getCurrent(){
        return current;
    }

    public void setCurrent(boolean current){
        this.current = current;
    }

    public GameParticipantController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(GameParticipantController playerController) {
        this.playerController = playerController;
    }

    public GameParticipantController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(GameParticipantController enemyController) {
        this.enemyController = enemyController;
    }
}
