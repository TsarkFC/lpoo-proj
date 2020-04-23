package Controller;

import Commands.DrawCardCommand;
import Model.Arena;
import Model.GameParticipant;

public class ArenaController {
    private Arena arena;

    public ArenaController(Arena arena){
        this.arena = arena;
    }

    public void playEnemyTurn(){ //TODO: Complete playTurn function with proper-ish AI
        if(arena.getEnemy().getPoints() < 8){

            DrawCardCommand command = new DrawCardCommand(arena, arena.getEnemy());
            command.execute();
        }
    }


}
