package Controller;

import Commands.DrawCardCommand;
import Model.Arena;
import Model.GameParticipant;

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


}
