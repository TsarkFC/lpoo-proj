package Commands;

import Model.Arena;

public class TurnChecker {
    private final Arena arena;
    public TurnChecker(Arena arena){
        this.arena = arena;
    }

    public void execute(){
        boolean current = this.arena.getCurrent();
        if(current){
            arena.getEnemy().playTurn(); //TODO: Complete playTurn function with proper-ish AI
        }



    }
}
