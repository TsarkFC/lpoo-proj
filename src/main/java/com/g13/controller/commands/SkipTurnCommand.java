package com.g13.controller.commands;

import com.g13.model.Card;
import com.g13.controller.GameParticipantController;

import java.util.Collections;
import java.util.List;

public class SkipTurnCommand implements Command{
    private GameParticipantController gameParticipantController;

    public SkipTurnCommand(GameParticipantController gameParticipantController){
        this.gameParticipantController = gameParticipantController;
    }

    public void execute() {
        gameParticipantController.setTurnOver(true);
    }
}