package controller.commands;

import controller.ArenaController;

public class TurnChecker implements Command{
    private ArenaController controller;
    public TurnChecker(ArenaController controller){
        this.controller = controller;
    }

    public void execute(){
        boolean current = this.controller.getCurrent();
        if(current){
            controller.getModel().setCurrent(false);
            controller.playEnemyTurn();
            controller.getModel().setCurrent(true);
        }
    }
}
