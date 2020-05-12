package com.g13.controller.strategies;

import com.g13.controller.ArenaController;

public interface PlayStrategy {

    boolean playTurn(ArenaController arenaController);

    boolean CheckStaticModifier(ArenaController arenaController, int cost, int modNum);

}
