package controller.strategies;

import controller.ArenaController;
import controller.GameParticipantController;
import model.Arena;
import model.GameParticipant;

public interface PlayStrategy {

    public boolean playTurn(ArenaController arenaController);

}
