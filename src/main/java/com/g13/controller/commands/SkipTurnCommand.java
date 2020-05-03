package com.g13.controller.commands;
import com.g13.controller.GameParticipantController;

public class SkipTurnCommand implements Command{
    private GameParticipantController controller;

    public SkipTurnCommand(GameParticipantController controller){
        this.controller = controller;
    }

    public void execute() {
        controller.setTurnOver(true);
    }
}