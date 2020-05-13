package com.g13.controller.strategies;

import com.g13.controller.ArenaController;
import com.g13.controller.commands.DrawCardCommand;


public class CarefulPlayStrategy implements PlayStrategy{

    int mana_saved = 10;

    @Override
    public boolean playTurn(ArenaController arenaController) {
        boolean has_drawn = false;
        if(arenaController.getEnemy().getPoints() <= arenaController.getEnemy().getMaxPoints() - 6){
            DrawCardCommand command = new DrawCardCommand(arenaController);
            command.execute();
            has_drawn = true;
        }
        return has_drawn;
    }

    @Override
    public boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum) {
        return mana_saved >= arenaController.getEnemy().getMana() - cost;
    }
}
