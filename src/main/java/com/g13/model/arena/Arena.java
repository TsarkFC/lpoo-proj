package com.g13.model.arena;
import com.g13.controller.arena.observer.Observer;
import com.g13.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Arena implements Model {
    private int width;
    private int height;
    private GameParticipant player;
    private Enemy enemy;

    private boolean isFinished;
    private List<Observer> observers;

    private boolean playersTurn = true;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
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

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void finish() {
        this.isFinished = true;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void setPlayer(GameParticipant player) {
        this.player = player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public List<Observer> getObservers(){return observers;}

    public boolean getPlayersTurn() { return playersTurn; }

    public void setPlayersTurn(boolean playersTurn) {
        this.playersTurn = playersTurn;
    }
}
