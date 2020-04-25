package Commands;

import Controller.ArenaController;
import Model.Arena;

public class TurnChecker {
    private final Arena arena;
    public TurnChecker(Arena arena){
        this.arena = arena;
    }

    public void execute(){
        boolean current = this.arena.getCurrent();
        if(current){
            this.arena.setCurrent(false);
            ArenaController controller = new ArenaController(arena);
            controller.playEnemyTurn();
            this.arena.setCurrent(true);
        }
    }
}
