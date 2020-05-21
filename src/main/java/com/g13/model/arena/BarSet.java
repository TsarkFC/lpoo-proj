package com.g13.model.arena;

public class BarSet {
    private Bar healthBar;
    private Bar manaBar;
    private Bar pointBar;

    public BarSet(Bar healthBar, Bar manaBar, Bar pointBar){
        this.healthBar = healthBar;
        this.manaBar = manaBar;
        this.pointBar = pointBar;
    }

     public int getHealth(){
        return healthBar.getValue();
     }
     public int getMaxHealth(){
        return healthBar.getMax_value();
     }
     public void setHealth(int health){healthBar.setValue(health);}

    public int getMana(){
        return manaBar.getValue();
    }
    public void setMana(int mana) {this.manaBar.setValue(mana);}
    public int getMaxMana(){
        return manaBar.getMax_value();
    }

    public int getPoints(){
        return pointBar.getValue();
    }
    public int getMaxPoints(){
        return pointBar.getMax_value();
    }
    public void setPoints(int points){
        pointBar.setValue(points);
    }
}
