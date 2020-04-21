package Model;

import Commands.ArenaObserver;
import Commands.TurnChecker;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

public class Arena {
    private int width;
    private int height;
    private Player player;   //to fix
    private Enemy enemy;     //to fix
    private boolean current; //to fix  true->player | false -> enemy

    private boolean isFinished = false;
    private List<ArenaObserver> observers;

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

    public void drawCard(GameParticipant part){



        part.drawCard();
        if(part.points > part.max_points){
            int a = min(player.points, 6);
            a = min(a, enemy.points);
            part.points = a;
            //TODO: End turn for both players function
            //TODO: Make variable with overdraw, normal and guarding states for ending the turn
            player.setTurnOver(true);
            enemy.setTurnOver(true);
        }
        if(part.points == part.max_points){
            part.setTurnOver(true);
        }

        //TODO: Use turn_over variables to check if they player's turn is over (using a command?)
        TurnChecker checker = new TurnChecker(this);
        checker.execute();

        notifyObservers();
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
}
