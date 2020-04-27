package model;

import observer.ArenaObserver;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private GameParticipant player;
    private GameParticipant enemy;
    private boolean current; //true->player | false -> enemy

    private boolean isFinished;
    private List<ArenaObserver> observers;

    public Arena(int width, int height){
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

    public GameParticipant getPlayer() {
        return player;
    }

    public GameParticipant getEnemy() {
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

    public void setPlayer(GameParticipant player) {
        this.player = player;
    }

    public void setEnemy(GameParticipant enemy) {
        this.enemy = enemy;
    }
}
