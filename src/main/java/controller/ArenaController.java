package controller;

import commands.DrawCardCommand;
import model.Arena;
import model.Enemy;
import model.GameParticipant;
import model.Player;

public class ArenaController {
    private Arena model;

    public ArenaController(Arena arena){
        this.model = arena;
    }

    public void playEnemyTurn(){ //TODO: Complete playTurn function with proper-ish AI
        if(model.getEnemy().getPoints() < 8){

            DrawCardCommand command = new DrawCardCommand(model, model.getEnemy());
            command.execute();
        }
    }

    public int getWidth(){ return model.getWidth(); }
    public int getHeight(){ return model.getHeight(); }

    public Player getPlayer() {return model.getPlayer();}
    public Enemy getEnemy() {return model.getEnemy();}
    public Arena getModel() {return model;}

    public boolean getCurrent() {return model.getCurrent();}

}
