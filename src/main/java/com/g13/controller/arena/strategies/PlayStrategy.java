package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;

abstract public class PlayStrategy {

    protected int mana_saved = 5;

    protected int health_to_heal = 2;

    protected double flux_percentage_accept = 0.75;

    protected boolean draw_limit_reached = false;

    abstract public boolean playTurn(ArenaController arenaController);

    public boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum) {
        int enemy_points = arenaController.getEnemy().getPoints();
        if(enemy_points <= arenaController.getPlayer().getPoints() && draw_limit_reached
            && enemy_points + modNum < arenaController.getEnemy().getMaxPoints())
            return HasEnoughManaToWantToPlay(arenaController, cost);
        return false;
    }

    public boolean CheckOverTimeHeal(ArenaController arenaController, int cost) {
        if(arenaController.getEnemy().getHealth() <= health_to_heal)
            return HasEnoughManaToWantToPlay(arenaController, cost);
        return false;
    }

    public boolean CheckFluxModifier(ArenaController arenaController, int cost, int minModNum, int maxModNum){
        int result =  (int) Math.ceil(flux_percentage_accept * (maxModNum - minModNum + 1) + minModNum);

        return result <= arenaController.getEnemy().getMaxPoints() - arenaController.getEnemy().getPoints()
                && HasEnoughManaToWantToPlay(arenaController, cost);
    }

    private boolean HasEnoughManaToWantToPlay(ArenaController arenaController, int cost){
        return mana_saved <= arenaController.getEnemy().getMana() - cost;
    }
}
