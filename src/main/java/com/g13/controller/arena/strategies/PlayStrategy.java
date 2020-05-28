package com.g13.controller.arena.strategies;

import com.g13.controller.arena.ArenaController;

abstract public class PlayStrategy {

    protected int mana_saved = 5;

    protected int health_to_heal = 2;

    protected double flux_percentage_accept = 0.75;

    protected double damage_percentage_accept = 0.33; //The higher, the more kill happy the ai is

    protected boolean draw_limit_reached = false;

    abstract public boolean playTurn(ArenaController arenaController);

    public boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum) {
        if(arenaController.getEnemy().getPoints() <= arenaController.getPlayer().getPoints() && draw_limit_reached)
            HasEnoughManaToWantToPlay(arenaController, cost);
        return false;
    }

    public boolean CheckInstantHeal(ArenaController arenaController, int cost) {
        if(arenaController.getEnemy().getHealth() <= health_to_heal)
            HasEnoughManaToWantToPlay(arenaController, cost);
        return false;
    }

    public boolean CheckOverTimeHeal(ArenaController arenaController, int cost) {
        if(arenaController.getEnemy().getHealth() <= health_to_heal)
            HasEnoughManaToWantToPlay(arenaController, cost);
        return false;
    }

    public boolean CheckFluxModifier(ArenaController arenaController, int cost, int minModNum, int maxModNum){
        int result =  (int) Math.ceil(flux_percentage_accept * (maxModNum - minModNum + 1) + minModNum);

        return result <= arenaController.getEnemy().getMaxPoints() - arenaController.getEnemy().getPoints() && HasEnoughManaToWantToPlay(arenaController, cost);
    }

    public boolean CheckInstantDamage(ArenaController arenaController, int cost, int minDamage, int maxDamage){
        int result =  (int) Math.floor(damage_percentage_accept * (maxDamage - minDamage + 1) + minDamage);

        return arenaController.getPlayer().getHealth()  <=  result || HasEnoughManaToWantToPlay(arenaController, cost);
    }

    private boolean HasEnoughManaToWantToPlay(ArenaController arenaController, int cost){
        return mana_saved <= arenaController.getEnemy().getMana() - cost;
    }
}
